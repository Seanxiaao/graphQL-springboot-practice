package com.seanxiaao.springbootwithgraphql.service;

import com.seanxiaao.springbootwithgraphql.entity.InternalShow;
import com.seanxiaao.springbootwithgraphql.entity.Show;

import java.util.List;

public interface ShowService {

    List<Show> shows(); // without actors

}


