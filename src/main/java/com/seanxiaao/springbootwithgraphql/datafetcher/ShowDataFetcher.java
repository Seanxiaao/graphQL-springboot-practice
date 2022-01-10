package com.seanxiaao.springbootwithgraphql.datafetcher;


import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.InputArgument;
import com.netflix.graphql.dgs.context.DgsContext;
import com.seanxiaao.springbootwithgraphql.context.MyContextBuilder;
import com.seanxiaao.springbootwithgraphql.entity.Actor;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.seanxiaao.springbootwithgraphql.entity.TitleFilter;
import com.seanxiaao.springbootwithgraphql.generated.DgsConstants;
import com.seanxiaao.springbootwithgraphql.service.ActorService;
import com.seanxiaao.springbootwithgraphql.service.ShowService;
import graphql.execution.DataFetcherResult;
import graphql.schema.DataFetchingEnvironment;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dataloader.DataLoader;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
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

//    /**
//     * using local host to cache the request.
//     * @param dfe
//     * @return
//     */
//    @DgsData(
//            parentType = DgsConstants.QUERY_TYPE,
//            field = DgsConstants.SHOW.Actors
//    )
//    public DataFetcherResult<List<Show>> shows(DataFetchingEnvironment dfe) {
//        List<Show> shows = showService.shows();
//
//        if (dfe.getSelectionSet().contains("actors")) {
//            Map<Show, List<Actor>> actorForShows = actorService.actorForShows(shows);
//            LOGGER.info("{}", actorForShows.toString());
//            return DataFetcherResult.<List<Show>>newResult().data(shows).localContext(actorForShows).build();
//        } else {
//            return DataFetcherResult.<List<Show>>newResult().data(shows).build();
//        }
//    }


    @DgsData(
            parentType = DgsConstants.QUERY_TYPE,
            field = DgsConstants.QUERY.Shows
    )
    public List<Show> shows(@InputArgument(name = "titleFilter",
            collectionType = TitleFilter.class) Optional<TitleFilter> titleFilter) {

        List<Show> shows = showService.shows();

        if (!titleFilter.isPresent()) {
            return shows;
        }

        return showService.shows().stream().filter(
                show -> this.matchFilter(titleFilter.get().getName(), show.getTitle())
        ).collect(Collectors.toList());
    }

    private boolean matchFilter(String titleFilter, String showName) {
        return StringUtils.containsIgnoreCase(showName,
                StringUtils.defaultIfBlank(titleFilter, StringUtils.EMPTY));
    }


    @DgsData(parentType = "Query", field = "withContext")
    public String withContext(DataFetchingEnvironment dfe) {
        MyContextBuilder.MyContext myContext = DgsContext.getCustomContext(dfe);
        return  myContext.getCustomState();
    }

    @DgsData(parentType = "Show", field = "actors")
    public CompletableFuture<Actor> actors(DgsDataFetchingEnvironment dfe) {
        Show show = dfe.getSource();
        DataLoader<Show, Actor> mappedBatchLoader = dfe.getDataLoader("actors");
        return mappedBatchLoader.load(show);
    }

}


