package com.example.intern.movieapp.mvp.Models;

public class MovieItem {
    private String voteCount;
    private String movieId;
    private String video;
    private String voteAverage;
    private String titleOfMovie;
    private String popularity;
    private String posterPath;
    private String language;
    private String titleOriginal;
    private String[] genreIds;
    private String backdropPath;
    private String adult;
    private String overview;
    private String releaseDate;

    private int id = -1;

    public MovieItem() {
    }

    public MovieItem(int id) {
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVoteCount(String voteCount) {
        this.voteCount = voteCount;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    public void setTitleOfMovie(String titleOfMovie) {
        this.titleOfMovie = titleOfMovie;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setTitleOriginal(String titleOriginal) {
        this.titleOriginal = titleOriginal;
    }

    public void setGenreIds(String[] genreIds) {
        this.genreIds = genreIds;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getId() {
        return id;
    }

    public String getAdult() {
        return adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getLanguage() {
        return language;
    }

    public String getMovieId() {
        return movieId;
    }

    public String getOverview() {
        return overview;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getTitleOfMovie() {
        return titleOfMovie;
    }

    public String getTitleOriginal() {
        return titleOriginal;
    }

    public String getVideo() {
        return video;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public String getVoteCount() {
        return voteCount;
    }

    public String[] getGenreIds() {
        return genreIds;
    }
}
