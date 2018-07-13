package com.example.intern.movieapp.mvp.ViewAndAdapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern.movieapp.R;
import com.example.intern.movieapp.mvp.MVP_API;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity implements MVP_API.ViewOperations {
    private TextView mTitle;
    private ImageView mMoviePoster;
    private TextView mSummary;
    private TextView mReleaseDate;
    private TextView mUserRating;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getViewDetails();
        setupViews(bundle);
    }

    private Bundle getViewDetails() {
        Bundle bundle = getIntent().getBundleExtra("movie-details");
        return bundle;
    }

    private void setupViews(Bundle bundle) {
        mTitle = findViewById(R.id.textViewTitle);
        mTitle.setText(bundle.getString("movie-title"));
        mMoviePoster = findViewById(R.id.imageViewPoster);
        Picasso.get().setLoggingEnabled(true);
        Picasso.get().load(bundle.getString("image-url")).into(mMoviePoster);
        mSummary = findViewById(R.id.textViewSummary);
        mSummary.setText(bundle.getString("summary"));
        mReleaseDate = findViewById(R.id.textViewReleaseDate);
        mReleaseDate.setText(bundle.getString("date"));
        mUserRating = findViewById(R.id.textViewUserRating);
        mUserRating.setText(bundle.getString("rating") + " / 10");
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
