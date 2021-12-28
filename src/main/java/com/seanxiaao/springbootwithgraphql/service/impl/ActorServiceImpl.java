package com.seanxiaao.springbootwithgraphql.service.impl;

import com.seanxiaao.springbootwithgraphql.entity.Actor;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.seanxiaao.springbootwithgraphql.service.ActorService;
import com.seanxiaao.springbootwithgraphql.service.ShowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Resource
    private ShowService showService;

    List<Actor> actors = Arrays.asList(
            new Actor("A", Collections.singletonList("Stranger Things")),
            new Actor("V", Arrays.asList("Stranger Things", "Ozark")),
            new Actor("L", Collections.singletonList("Ozark"))
    );

    @Override
    public List<Actor> forShow(String showName) {
        List<Show> shows = showService.shows();
        // assume that showName is unique for every show, else should handle
        // multiple returning errors
        return actors.stream().filter(actor -> actor.getShowName()
                .contains(showName)).collect(Collectors.toList());

    }

    @Override
    public Map<Show, List<Actor>> actorForShows(List<Show> showNames) {

        Map<Show, List<Actor>> actorMap = new HashMap<>();
        Map<String, Show> showMap = showNames.stream().collect(Collectors.toMap(Show::getTitle, Function.identity()));

        actors.forEach(actor -> {
            List<String> actorShowNames = actor.getShowName();
            actorShowNames.forEach(
                    actorShowName -> {
                        if (showMap.containsKey(actorShowName)) {
                            Show show = showMap.get(actorShowName);
                            actorMap.putIfAbsent(show, new ArrayList<>());
                            actorMap.get(show).add(actor);
                        }
                    }
            );
        });

        return actorMap;
    }


}
