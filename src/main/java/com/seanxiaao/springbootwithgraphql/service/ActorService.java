package com.seanxiaao.springbootwithgraphql.service;

import com.seanxiaao.springbootwithgraphql.entity.Actor;
import com.seanxiaao.springbootwithgraphql.entity.Show;

import java.util.List;
import java.util.Map;


public interface ActorService {

    List<Actor> forShow(String showName);

    Map<Show, List<Actor>> actorForShows(List<Show> showNames);

}
