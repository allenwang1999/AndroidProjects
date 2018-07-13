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
    private List<MovieItem> movieItemsList;
    public MainModel(MVPAPI.PModelOperations presenter) {
        this.mPresenter = presenter;
        movieItemsList = new ArrayList<>();
    }

    @Override
    public boolean hasItems() {
        return movieItemsList != null;
    }

    @Override
    public int getItemCount() {
        if(movieItemsList != null) {
            return movieItemsList.size();
        } else {
            return 0;
        }
    }

    @Override
    public String getMovieTitle(int index) {
        return movieItemsList.get(index).getTitle();
    }

    @Override
    public String getOriginalTitle(int index) {
        return movieItemsList.get(index).getOriginal_title();
    }

    @Override
    public String getRating(int index) {
        return movieItemsList.get(index).getVote_average();
    }

    @Override
    public String getReleaseDate(int index) {
        return movieItemsList.get(index).getRelease_date();
    }

    @Override
    public String getImageLocation(int index) {
        return movieItemsList.get(index).getPoster_path();
    }

    @Override
    public String getSummary(int index) {
        return movieItemsList.get(index).getOverview();
    }

    @Override
    public String getImageUrl(String imageLocation) {
        String urlString = null;
        String size = "w185";
        try {
            URL url  = NetworkUtils.buildImageUrl(imageLocation, size);
            urlString = url.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlString;
    }

    @Override
    public String getImageUrlLarge(String imageLocation) {
        String urlString = "";
        String size = "w342";
        try {
            URL url = NetworkUtils.buildImageUrl(imageLocation, size);
            if(url != null) {
                urlString = url.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlString;
    }


    @Override
    public boolean loadMovieData(int page) {
        try {
            String jsonResult = NetworkUtils.getResponseFromUrl(NetworkUtils.getMoviesURL(page));
            List<MovieItem> movieItems = ParseJsonUtils.getMovieValuesFromJSON(jsonResult);
            assert movieItems != null;
            this.movieItemsList.addAll(movieItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieItemsList != null;
    }
}
