package com.example.intern.movieapp.mvp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.intern.movieapp.mvp.Models.MovieItem;
import com.example.intern.movieapp.mvp.Utils.NetworkUtils;
import com.example.intern.movieapp.mvp.Utils.ParseJsonUtils;
import com.example.intern.movieapp.mvp.data.FavoritesDbHelper;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.intern.movieapp.mvp.data.FavoritesDbHelper.COL_2;

public class MainModel implements MVP_API.ModelOperations {
    private MVP_API.PModelOperations mPresenter;
    private List<MovieItem> movieItemsList;
    private FavoritesDbHelper dbHelper;
    public MainModel(MVP_API.PModelOperations presenter) {
        this.mPresenter = presenter;
        movieItemsList = new ArrayList<>();
        dbHelper = new FavoritesDbHelper(mPresenter.getAppContext());
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

    @Override
    public void loadIntoDatabase(Bundle bundle) {
        dbHelper.insertData(bundle.getString("movie-title"), bundle.getString("image-url"), bundle.getString("large-image-url"), bundle.getString("rating"),
                bundle.getString("date"), bundle.getString("summary"));
    }

    @Override
    public void deleteFromDatabase(String string) {
        dbHelper.deleteData(string);
    }

    @Override
    public boolean existsInDatabase(String string) {
        return dbHelper.existsInDatabase(string);
    }
}
