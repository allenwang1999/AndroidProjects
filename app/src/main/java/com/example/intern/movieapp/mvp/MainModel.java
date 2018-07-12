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
    public String getMovieTitle(int position) {
        return movieItems.get(position).getTitle();
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
