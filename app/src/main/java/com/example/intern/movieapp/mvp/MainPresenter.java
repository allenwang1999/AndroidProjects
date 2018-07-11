package com.example.intern.movieapp.mvp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.intern.movieapp.R;
import com.example.intern.movieapp.mvp.ViewAndAdapter.MainActivity;
import com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders.MovieViewHolder;

import java.lang.ref.WeakReference;

public class MainPresenter implements MVPAPI.PViewOperations, MVPAPI.PModelOperations {
    private WeakReference<MVPAPI.ViewOperations> mView;
    private MVPAPI.ModelOperations mModel;

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
        holder.textView.setText("Test");
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
