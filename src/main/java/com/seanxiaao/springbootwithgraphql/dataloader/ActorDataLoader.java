package com.seanxiaao.springbootwithgraphql.dataloader;

import com.netflix.graphql.dgs.DgsDataLoader;
import com.seanxiaao.springbootwithgraphql.entity.Actor;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.seanxiaao.springbootwithgraphql.service.ActorService;
import org.dataloader.MappedBatchLoader;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@DgsDataLoader(name = "actors")
public class ActorDataLoader implements MappedBatchLoader<Show, List<Actor>>{

    @Resource
    private ActorService actorService;

    @Override
    public CompletionStage<Map<Show, List<Actor>>> load(Set<Show> set) {
        return CompletableFuture.supplyAsync(() -> actorService.actorForShows(new ArrayList<>(set)));
    }
}
