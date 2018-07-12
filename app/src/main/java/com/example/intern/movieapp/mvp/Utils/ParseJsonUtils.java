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
        private List<MovieItem> results;
        private int page = -1;
        private int total_results = -1;

        public void setPage(int page) {
            this.page = page;
        }

        public void setMovieItems(List<MovieItem> results) {
            this.results = results;
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
            return results;
        }
    }


}
