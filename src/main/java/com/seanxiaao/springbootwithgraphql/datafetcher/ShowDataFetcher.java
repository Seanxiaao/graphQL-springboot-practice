package com.seanxiaao.springbootwithgraphql.datafetcher;


import com.netflix.graphql.dgs.*;
import com.seanxiaao.springbootwithgraphql.entity.Actor;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.seanxiaao.springbootwithgraphql.service.ActorService;
import com.seanxiaao.springbootwithgraphql.service.ShowService;
import graphql.execution.DataFetcherResult;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @DgsComponent acts like @RestController in rest design
 * it's a class annotation
 */
@DgsComponent
public class ShowDataFetcher {

    private static final Logger LOGGER = LogManager.getLogger(ShowDataFetcher.class);

    @Resource
    private ActorService actorService;

    @Resource
    private ShowService showService;

    @DgsData(parentType = "Query", field = "shows")
    public DataFetcherResult<List<Show>> shows(DataFetchingEnvironment dfe) {
        List<Show> shows = showService.shows();

        if (dfe.getSelectionSet().contains("actors")) {
            Map<Show, List<Actor>> actorForShows = actorService.actorForShows(shows);
            LOGGER.info("{}", actorForShows.toString());
            return DataFetcherResult.<List<Show>>newResult().data(shows).localContext(actorForShows).build();
        } else {
            return DataFetcherResult.<List<Show>>newResult().data(shows).build();
        }
    }

    @DgsData(parentType = "Show", field = "actors")
    public List<Actor> actors(DgsDataFetchingEnvironment dfe) {
        Show show = dfe.getSource();
        Map<Show, List<Actor>> actorsForShows = dfe.getLocalContext(); // pre-loaded localContext in the above method
        return actorsForShows.get(show);
    }

}


