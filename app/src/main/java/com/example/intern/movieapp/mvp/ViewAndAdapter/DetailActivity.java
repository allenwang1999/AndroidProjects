package com.example.intern.movieapp.mvp.ViewAndAdapter;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern.movieapp.R;
import com.example.intern.movieapp.mvp.MVPAPI;
import com.example.intern.movieapp.mvp.MainModel;
import com.example.intern.movieapp.mvp.MainPresenter;

public class DetailActivity extends AppCompatActivity implements MVPAPI.ViewOperations {
    private static MVPAPI.PViewOperations mPresenter;
    private TextView mTitle;
    private ImageView mMoviePoster;
    private TextView mSummary;
    private TextView mReleaseDate;
    private TextView mUserRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupViews();
        setupMVP();
        getViewDetails();
    }

    private void getViewDetails() {
        Bundle bundle = getIntent().getBundleExtra("movie-details");
    }

    private void setupMVP() {

    }
    private void setupViews() {
        mTitle = findViewById(R.id.textViewTitle);
        mMoviePoster = findViewById(R.id.imageViewPoster);
        mSummary = findViewById(R.id.textViewSummary);
        mReleaseDate = findViewById(R.id.textViewReleaseDate);
        mUserRating = findViewById(R.id.textViewUserRating);
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public void showToast(Toast toast) {
        toast.show();
    }

    @Override
    public void notifyDataSetChanged() {
        notifyDataSetChanged();
    }
}
