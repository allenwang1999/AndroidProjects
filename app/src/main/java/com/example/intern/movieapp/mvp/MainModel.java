package com.example.intern.movieapp.mvp;

import android.content.Context;
import android.net.Network;

import com.example.intern.movieapp.mvp.Models.MovieItem;
import com.example.intern.movieapp.mvp.Utils.NetworkUtils;
import com.example.intern.movieapp.mvp.Utils.ParseJsonUtils;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainModel implements MVPAPI.ModelOperations {
    private MVPAPI.PModelOperations mPresenter;
    public List<MovieItem> movieItems;
    public MainModel(MVPAPI.PModelOperations presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean hasItems() {
        return movieItems != null;
    }

    @Override
    public int getItemCount() {
        if(movieItems != null) {
            return movieItems.size();
        } else {
            return 0;
        }
    }

    @Override
    public String getMovieTitle(int index) {
        return movieItems.get(index).getTitle();
    }

    @Override
    public String getOriginalTitle(int index) {
        return movieItems.get(index).getOriginal_title();
    }

    @Override
    public String getRating(int index) {
        return movieItems.get(index).getVote_average();
    }

    @Override
    public String getReleaseDate(int index) {
        return movieItems.get(index).getRelease_date();
    }

    @Override
    public String getImageLocation(int index) {
        return movieItems.get(index).getPoster_path();
    }

    @Override
    public String getSummary(int index) {
        return movieItems.get(index).getOverview();
    }

    @Override
    public String getImageUrl(String imageLocation) {
        String urlString = null;
        try {
            URL url  = NetworkUtils.buildImageUrl(imageLocation);
            urlString = url.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlString;
    }

    @Override
    public boolean loadMovieData() {
        try {
            String jsonResult = NetworkUtils.getResponseFromUrl(NetworkUtils.getMoviesURL());
            this.movieItems = ParseJsonUtils.getMovieValuesFromJSON(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieItems != null;
    }
}
