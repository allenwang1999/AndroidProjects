package com.example.intern.movieapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.intern.movieapp.R;
import com.example.intern.movieapp.mvp.Models.MovieItem;
import com.example.intern.movieapp.mvp.Utils.LayoutUtils;
import com.example.intern.movieapp.mvp.ViewAndAdapter.DetailActivity;
import com.example.intern.movieapp.mvp.ViewAndAdapter.MainActivity;
import com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders.MovieViewHolder;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class MainPresenter implements MVP_API.PViewOperations, MVP_API.PModelOperations {
    private WeakReference<MVP_API.ViewOperations> mView;
    private MVP_API.ModelOperations mModel;
    private static final int LOADER_ID = 2;
    private Parcelable mSavedInstanceState;
    private GridLayoutManager mLayoutManager;

    public MainPresenter(MVP_API.ViewOperations view) {
        mView = new WeakReference<>(view);
    }

    private MVP_API.ViewOperations getView() throws NullPointerException {
        if(mView != null) return mView.get();
        else throw new NullPointerException("View is unavailable");
    }

    public void setView(MVP_API.ViewOperations view) {
        mView = new WeakReference<>(view);
    }

    public void setModel(MVP_API.ModelOperations model, ArrayList<MovieItem> movieItemsList) {
        mModel = model;
        if(movieItemsList == null) {
            loadData(1);
        } else {
            mModel.setArrayList(movieItemsList);
        }
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
        int padding = LayoutUtils.getPadding(getView().getAppContext());
        holder.imageView.setPadding(padding, 0, padding, 0);
        String posterLocation = mModel.getImageLocation(position);
        String posterUrl = mModel.getImageUrlLarge(posterLocation);
        if(posterUrl != null && posterUrl.length() != 0) {
            Picasso.get().load(posterUrl).placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
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
        final String EXISTS_IN_DATABASE = "exists-in-database";
        final String IMAGE_URL_LARGE = "large-image-url";
        final String ITEM_ID = "item-id";
        Bundle bundle = new Bundle();
        String movieTitle = mModel.getOriginalTitle(position);
        String posterLocation = mModel.getImageLocation(position);
        String imageUrl = mModel.getImageUrl(posterLocation);
        String summary = mModel.getSummary(position);
        String rating = mModel.getRating(position);
        String date = mModel.getReleaseDate(position);
        String largePosterLocation = mModel.getImageLocation(position);
        String largePosterUrl = mModel.getImageUrlLarge(largePosterLocation);
        boolean exists = mModel.existsInDatabase(movieTitle);
        bundle.putString(IMAGE_URL_LARGE, largePosterUrl);
        bundle.putBoolean(EXISTS_IN_DATABASE, exists);
        bundle.putString(MOVIE_TITLE, movieTitle);
        bundle.putString(IMAGE_URL, imageUrl);
        bundle.putString(SUMMARY, summary);
        bundle.putString(RATING, rating);
        bundle.putString(DATE, date);
        bundle.putInt(ITEM_ID, mModel.getItemId(position));
        return bundle;
    }

    @Override
    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        loadData(page);
        getView().notifyDataSetChanged();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Bundle bundle = data.getBundleExtra("movie-details");
            boolean favorite = data.getBooleanExtra("favorite", false);
            if(favorite) {
                mModel.loadIntoDatabase(bundle);
            } else {
                mModel.deleteFromDatabase(bundle.getString("movie-title"));
                if(bundle.getInt("item-id") == 1) {
                    mModel.deleteFromList(bundle.getString("movie-title"));
                }

            }
        }
        getView().notifyDataSetChanged();
    }

    @Override
    public void clearViews(int itemCount) {
        if(itemCount > 0) mModel.clearItems();
    }

    @Override
    public void showFavoriteViews() {
        mModel.showFavoriteViews();
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

    @Override
    public ArrayList<MovieItem> getMovieItemsListToView() {
        return mModel.getMovieItemsList();
    }

}
