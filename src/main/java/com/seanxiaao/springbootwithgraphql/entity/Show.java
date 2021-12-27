package com.seanxiaao.springbootwithgraphql.entity;

import java.util.List;

public class Show {

    private String title;
    private int releaseYear;

    public Show(String title, int releaseYear) {
        this.title = title;
        this.releaseYear = releaseYear;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

}
