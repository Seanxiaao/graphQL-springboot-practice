package com.seanxiaao.springbootwithgraphql.entity;

public class ShowWithId {

    private int showId;
    private Show show;

    public String getTitle() {
        return show.getTitle();
    }

    public static ShowWithId fromInternalShow(InternalShow internalShow) {
        ShowWithId showWithId = new ShowWithId();
        Show show = new Show(internalShow.getTitle(), internalShow.getReleaseYear());
        showWithId.showId = internalShow.getShowId();
        showWithId.show = show;
        return showWithId;
    }

}
