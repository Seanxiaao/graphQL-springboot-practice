package com.seanxiaao.springbootwithgraphql.service;

import com.seanxiaao.springbootwithgraphql.entity.Actor;
import com.seanxiaao.springbootwithgraphql.entity.InternalShow;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;

import java.util.List;
import java.util.Map;


public interface ActorService {

    List<Actor> forShow(String showName);

    Map<Show, List<Actor>> actorForShows(List<Show> showNames);

}
