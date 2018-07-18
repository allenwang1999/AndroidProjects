package com.example.intern.movieapp.mvp.ViewAndAdapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
    private Button mButton;

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

    private void setupViews(final Bundle bundle) {
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
        mButton = findViewById(R.id.buttonFavorite);
        if(bundle.getBoolean("exists-in-database")) {
            mButton.setText("Favorited!");
        } else {
            mButton.setText("Favorite");
        }
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = getIntent();
                if(!mButton.getText().toString().equals("Favorited!")) {
                    mButton.setText("Favorited!");
                    mButton.setTextColor(getResources().getColor(R.color.colorPrimary));
                    returnIntent.putExtra("favorite", true);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                } else {
                    mButton.setText("Favorite");
                    mButton.setTextColor(getResources().getColor(R.color.colorAccent));
                    returnIntent.putExtra("favorite", false);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }
        });
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
