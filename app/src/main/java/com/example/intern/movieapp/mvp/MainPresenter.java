package com.example.intern.movieapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern.movieapp.R;
import com.example.intern.movieapp.mvp.Models.MovieItem;
import com.example.intern.movieapp.mvp.ViewAndAdapter.DetailActivity;
import com.example.intern.movieapp.mvp.ViewAndAdapter.MainActivity;
import com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders.MovieViewHolder;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MainPresenter implements MVPAPI.PViewOperations, MVPAPI.PModelOperations {
    private WeakReference<MVPAPI.ViewOperations> mView;
    private MVPAPI.ModelOperations mModel;
    private static final int LOADER_ID = 2;

    public MainPresenter(MVPAPI.ViewOperations view) {
        mView = new WeakReference<>(view);
    }

    private MVPAPI.ViewOperations getView() throws NullPointerException {
        if(mView != null) return mView.get();
        else throw new NullPointerException("View is unavailable");
    }

    public void setView(MVPAPI.ViewOperations view) {
        mView = new WeakReference<>(view);
    }

    public void setModel(MVPAPI.ModelOperations model) {
        mModel = model;
        loadData(1);
    }

    private void loadData(final int page) {
        try {
            new AsyncTask<Void, Void, Boolean>() {
                @Override
                protected Boolean doInBackground(Void... voids) {
                    return mModel.loadMovieData(page);
                }

                @Override
                protected void onPostExecute(Boolean aBoolean) {
                    if (!aBoolean) {
                        getView().showToast(Toast.makeText(getActivityContext(), "Error Loading Data.", Toast.LENGTH_SHORT));
                    } else {
                        getView().notifyDataSetChanged();
                    }
                }
            }.execute();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mModel.getItemCount();
    }

    @Override
    public MovieViewHolder createViewHolder(ViewGroup parent, int viewType) {
        MovieViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewTaskRow = inflater.inflate(R.layout.movie_view, parent, false);
        viewHolder = new MovieViewHolder(viewTaskRow);
        return viewHolder;
    }

    @Override
    public void bindViewHolder(MovieViewHolder holder, int position) {
        String posterLocation = mModel.getImageLocation(position);
        String posterUrl = mModel.getImageUrlLarge(posterLocation);
        if(posterUrl != null && posterUrl.length() != 0) {
            Picasso.get().load(posterUrl).into(holder.imageView);
        }
    }

    @Override
    public Intent setOnClickListener(MovieViewHolder holder, int position) {
        Bundle movieDetails;
        movieDetails = getViewDetails(position);
        Intent detailActivityIntent = new Intent(getActivityContext(), DetailActivity.class);
        detailActivityIntent.putExtra("movie-details", movieDetails);
        return detailActivityIntent;
    }

    @Override
    public Bundle getViewDetails(int position) {
        final String MOVIE_TITLE = "movie-title";
        final String IMAGE_URL = "image-url";
        final String SUMMARY = "summary";
        final String RATING = "rating";
        final String DATE = "date";

        Bundle bundle = new Bundle();
        String movieTitle = mModel.getOriginalTitle(position);
        String posterLocation = mModel.getImageLocation(position);
        String imageUrl = mModel.getImageUrl(posterLocation);
        String summary = mModel.getSummary(position);
        String rating = mModel.getRating(position);
        String date = mModel.getReleaseDate(position);
        bundle.putString(MOVIE_TITLE, movieTitle);
        bundle.putString(IMAGE_URL, imageUrl);
        bundle.putString(SUMMARY, summary);
        bundle.putString(RATING, rating);
        bundle.putString(DATE, date);
        return bundle;
    }

    @Override
    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        loadData(page);
        getView().notifyDataSetChanged();
    }


    @Override
    public Context getAppContext() {
        try {
            return getView().getAppContext();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Context getActivityContext() {
        try {
            return getView().getActivityContext();
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }

}
