package com.example.intern.movieapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern.movieapp.mvp.Models.MovieItem;
import com.example.intern.movieapp.mvp.ViewAndAdapter.MainActivity;
import com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders.MovieViewHolder;

import java.net.URL;
import java.util.List;

public class MVP_API {

    public interface ViewOperations {
        Context getAppContext();
        Context getActivityContext();
        void showToast(Toast toast);
        void notifyDataSetChanged();
    }

    public interface PViewOperations {
        int getItemCount();
       MovieViewHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(MovieViewHolder holder, int position);
        Intent setOnClickListener(MovieViewHolder holder, int position);
        Bundle getViewDetails(int position);
        void onLoadMore(int page, int totalItemsCount, RecyclerView view);
        void onActivityResult(int requestCode, int resultCode, Intent data);
    }

    public interface PModelOperations {
        Context getAppContext();
        Context getActivityContext();
    }

    public interface ModelOperations {
        int getItemCount();
        boolean loadMovieData(int page);
        boolean hasItems();
        String getMovieTitle(int index);
        String getOriginalTitle(int index);
        String getRating(int index);
        String getReleaseDate(int index);
        String getImageLocation(int index);
        String getSummary(int index);
        String getImageUrl(String imageLocation);
        String getImageUrlLarge(String imageLocation);
        void loadIntoDatabase(Bundle bundle);
        void deleteFromDatabase(String string);
        boolean existsInDatabase(String string);
    }
}
