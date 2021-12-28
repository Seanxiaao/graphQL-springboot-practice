package com.seanxiaao.springbootwithgraphql.service.impl;


import com.seanxiaao.springbootwithgraphql.entity.InternalShow;
import com.seanxiaao.springbootwithgraphql.entity.Show;
import com.seanxiaao.springbootwithgraphql.service.ShowService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    @Override
    public List<Show> shows() {
        return Arrays.asList(
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
