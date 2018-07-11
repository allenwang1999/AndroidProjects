package com.example.intern.movieapp.mvp;

import android.content.Context;
import android.net.Network;

import com.example.intern.movieapp.mvp.Models.MovieItem;
import com.example.intern.movieapp.mvp.Utils.NetworkUtils;
import com.example.intern.movieapp.mvp.Utils.ParseJsonUtils;

import java.util.ArrayList;
import java.util.List;

public class MainModel implements MVPAPI.ModelOperations {
    private MVPAPI.PModelOperations mPresenter;
    public List<MovieItem> movieItems;
    public MainModel(MVPAPI.PModelOperations presenter) {
        this.mPresenter = presenter;
    }
    @Override
    public int getItemCount() {
        if(movieItems != null) {
            return movieItems.size();
        } else {
            return 0;
        }
    }
    //TODO put results from JSON to recyclerview

    @Override
    public List<MovieItem> loadMovieData() {
        try {
            String jsonResult = NetworkUtils.getResponseFromUrl(NetworkUtils.getMoviesURL());
            movieItems = ParseJsonUtils.getMovieValuesFromJSON(jsonResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movieItems;
    }
}
