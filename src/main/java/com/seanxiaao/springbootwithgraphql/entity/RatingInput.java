package com.seanxiaao.springbootwithgraphql.entity;

public class RatingInput {
    private String title;
    private int stars;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

}
