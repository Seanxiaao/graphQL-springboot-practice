package com.seanxiaao.springbootwithgraphql.entity;

import java.util.List;

public class Actor {

    private String name;

    private List<String> showName;

    public Actor(String name, List<String> showName) {
        this.name = name;
        this.showName = showName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getShowName() {
        return showName;
    }

    public void setShowName(List<String> showName) {
        this.showName = showName;
    }
}
