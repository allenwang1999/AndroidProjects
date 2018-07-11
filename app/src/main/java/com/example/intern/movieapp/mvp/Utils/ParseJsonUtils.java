package com.example.intern.movieapp.mvp.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Network;

import com.example.intern.movieapp.mvp.Models.MovieItem;
import com.example.intern.movieapp.mvp.ViewAndAdapter.MainActivity;
import com.google.gson.Gson;

import org.json.JSONException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ParseJsonUtils {

    //JSON Keys
    private static final String VOTE_COUNT = "vote_count";
    private static final String MOVIE_ID = "id";
    private static final String VIDEO = "video";
    private static final String VOTE_AVERAGE = "vote_average";
    private static final String TITLE = "title";
    private static final String POPULARITY = "popularity";
    private static final String POSTER_PATH = "poster_path";
    private static final String LANGUAGE = "original_language";
    private static final String TITLE_ORIGINAL = "original_title";
    private static final String GENRE_IDS = "genre_ids";
    private static final String BACKDROP_PATH = "backdrop_path";
    private static final String ADULT = "adult";
    private static final String OVERVIEW = "overview";
    private static final String RELEASE_DATE = "release_date";

    public static final String detailsArray = "results";

    private static List<MovieItem> movieItems;

    public static List<MovieItem> getMovieValuesFromJSON(String jsonString){
        Gson gson = new Gson();
        try {
            MovieValueHelper movieValueHelper = gson.fromJson(jsonString, MovieValueHelper.class);
            movieItems = movieValueHelper.getMovieItems();
            return movieItems;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public class MovieValueHelper {
        private List<MovieItem> movieItems;
        private int page = -1;
        private int total_results = -1;

        public void setPage(int page) {
            this.page = page;
        }

        public void setMovieItems(List<MovieItem> movieItems) {
            this.movieItems = movieItems;
        }

        public void setTotal_results(int total_results) {
            this.total_results = total_results;
        }

        public int getPage() {
            return page;
        }

        public int getTotal_results() {
            return total_results;
        }

        public List<MovieItem> getMovieItems() {
            return movieItems;
        }
    }


}
