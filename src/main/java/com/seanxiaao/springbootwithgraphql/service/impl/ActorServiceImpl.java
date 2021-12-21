package com.seanxiaao.springbootwithgraphql.service.impl;

import com.seanxiaao.springbootwithgraphql.entity.Actor;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.seanxiaao.springbootwithgraphql.service.ActorService;
import com.seanxiaao.springbootwithgraphql.service.ShowService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActorServiceImpl implements ActorService {

    @Resource
    private ShowService showService;

    List<Actor> actors = com.sun.tools.javac.util.List.of(
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
}
