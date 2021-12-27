package com.seanxiaao.springbootwithgraphql.service.impl;


import com.seanxiaao.springbootwithgraphql.entity.InternalShow;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.seanxiaao.springbootwithgraphql.service.ShowService;
import com.sun.tools.javac.util.List;
import org.springframework.stereotype.Service;

@Service
public class ShowServiceImpl implements ShowService {

    @Override
    public List<Show> shows() {
        return List.of(
                new Show("Stranger Things", 2016),
                new Show("Ozark", 2017),
                new Show("The Crown", 2016),
                new Show("Dead to Me", 2019),
                new Show("Orange is the New Black", 2013)
        );
    }

    public List<InternalShow> internalShows() {
        return null;
    }

}
