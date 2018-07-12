package com.example.intern.movieapp.mvp.Models;

public class MovieItem {
    private String vote_count;
    private String id;
    private String video;
    private String vote_average;
    private String title;
    private String popularity;
    private String poster_path;
    private String language;
    private String original_title;
    private String[] genre_ids;
    private String backdrop_path;
    private String adult;
    private String overview;
    private String release_date;

    private int itemId = -1;

    public MovieItem() {
    }

    public MovieItem(int itemId) {
        this.itemId = itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setGenre_ids(String[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getVideo() {
        return video;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getOverview() {
        return overview;
    }

    public String getLanguage() {
        return language;
    }

    public String getAdult() {
        return adult;
    }

    public int getItemId() {
        return itemId;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getTitle() {
        return title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public String getVote_count() {
        return vote_count;
    }

    public String[] getGenre_ids() {
        return genre_ids;
    }
}
