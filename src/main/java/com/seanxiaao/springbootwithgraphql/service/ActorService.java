package com.seanxiaao.springbootwithgraphql.service;

import com.seanxiaao.springbootwithgraphql.entity.Actor;

import java.util.List;


public interface ActorService {

    List<Actor> forShow(String showName);

}
