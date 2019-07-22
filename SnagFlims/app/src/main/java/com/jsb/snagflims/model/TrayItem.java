package com.jsb.snagflims.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TrayItem {

    @SerializedName("average_viewer_grade")
    @Expose
    private String averageViewerGrade;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("parental_rating")
    @Expose
    private String parentalRating;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("no_of_episodes")
    @Expose
    private Integer noOfEpisodes;
    @SerializedName("poster")
    @Expose
    private String poster;
    @SerializedName("grade_count")
    @Expose
    private Integer gradeCount;
    @SerializedName("banner_image")
    @Expose
    private String bannerImage;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("hd")
    @Expose
    private Boolean hd;
    @SerializedName("year")
    @Expose
    private Integer year;
    @SerializedName("no_of_seasons")
    @Expose
    private Integer noOfSeasons;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("double_wide")
    @Expose
    private String doubleWide;

    public String getAverageViewerGrade() {
        return averageViewerGrade;
    }

    public void setAverageViewerGrade(String averageViewerGrade) {
        this.averageViewerGrade = averageViewerGrade;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParentalRating() {
        return parentalRating;
    }

    public void setParentalRating(String parentalRating) {
        this.parentalRating = parentalRating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNoOfEpisodes() {
        return noOfEpisodes;
    }

    public void setNoOfEpisodes(Integer noOfEpisodes) {
        this.noOfEpisodes = noOfEpisodes;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getGradeCount() {
        return gradeCount;
    }

    public void setGradeCount(Integer gradeCount) {
        this.gradeCount = gradeCount;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getHd() {
        return hd;
    }

    public void setHd(Boolean hd) {
        this.hd = hd;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getNoOfSeasons() {
        return noOfSeasons;
    }

    public void setNoOfSeasons(Integer noOfSeasons) {
        this.noOfSeasons = noOfSeasons;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoubleWide() {
        return doubleWide;
    }

    public void setDoubleWide(String doubleWide) {
        this.doubleWide = doubleWide;
    }

}
