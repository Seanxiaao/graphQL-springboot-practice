package com.seanxiaao.springbootwithgraphql.datafetcher;


import com.netflix.graphql.dgs.*;
import com.seanxiaao.springbootwithgraphql.entity.Actor;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.seanxiaao.springbootwithgraphql.service.ActorService;
import com.seanxiaao.springbootwithgraphql.service.ShowService;
import graphql.execution.DataFetcherResult;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @DgsComponent acts like @RestController in rest design
 * it's a class annotation
 */
@DgsComponent
public class ShowDataFetcher {

    @Resource
    private ActorService actorService;


    @Resource
    private ShowService showService;

    /**
     * @param titleFilter
     * @return
     * @DgsQuery acts like @GetMapping in rest degisn, where @InputArgument acts like @RequestParams
     * One query return all object.
     */
    @DgsQuery
    public List<Show> shows(@InputArgument String titleFilter) {
        List<Show> shows = showService.shows();
        if (titleFilter == null) {
            return shows;
        }

        return shows.stream().filter(s -> s.getTitle().contains(titleFilter))
                .collect(Collectors.toList());
    }


    @DgsData(parentType = "Query", field = "shows")
    public DataFetcherResult<List<Show>> show(@InputArgument String titleFilter) {
        List<Show> shows = showService.shows();

        return DataFetcherResult.newResult().data(shows).localContext()
    }

    /**
     * @return
     * @DgsData acts like @RequestMapping
     * This query can strip off complicated fields that may require some other query to do
     * I mean at this sample, actors might be stripped off.
     */
    @DgsData(parentType = "Query", field = "shows")
    public List<Show> shows() {
        List<Show> shows = showService.shows();
        return shows;
    }

    @DgsData(parentType = "Show", field = "actors")
    public List<Actor> actors(DgsDataFetchingEnvironment dgsDataFetchingEnvironment) {
        Show show = dgsDataFetchingEnvironment.getSource(); // this method require there is an Id like field in entity
        return actorService.forShow(show.getTitle());
    }

}


