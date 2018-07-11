package com.example.intern.movieapp.mvp;

import android.content.Context;
import android.view.ViewGroup;

import com.example.intern.movieapp.mvp.Models.MovieItem;
import com.example.intern.movieapp.mvp.ViewAndAdapter.MainActivity;
import com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders.MovieViewHolder;

import java.util.List;

public class MVPAPI {

    public interface ViewOperations {
        Context getAppContext();
        Context getActivityContext();
    }

    public interface PViewOperations {
        int getItemCount();
       MovieViewHolder createViewHolder(ViewGroup parent, int viewType);
        void bindViewHolder(MovieViewHolder holder, int position);
    }

    public interface PModelOperations {
        Context getAppContext();
        Context getActivityContext();
    }

    public interface ModelOperations {
        int getItemCount();
        List<MovieItem> loadMovieData();
    }
}
