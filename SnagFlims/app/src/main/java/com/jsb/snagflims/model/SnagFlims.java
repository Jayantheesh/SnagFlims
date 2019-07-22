package com.jsb.snagflims.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SnagFlims {

    @SerializedName("featured_shows")
    @Expose
    private List<FeaturedShow> featuredShows = null;

    public List<FeaturedShow> getFeaturedShows() {
        return featuredShows;
    }

    public void setFeaturedShows(List<FeaturedShow> featuredShows) {
        this.featuredShows = featuredShows;
    }

}