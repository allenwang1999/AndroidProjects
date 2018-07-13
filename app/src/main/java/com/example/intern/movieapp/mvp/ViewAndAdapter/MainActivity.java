package com.example.intern.movieapp.mvp.ViewAndAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.intern.movieapp.R;
import com.example.intern.movieapp.mvp.MVP_API;
import com.example.intern.movieapp.mvp.MainModel;
import com.example.intern.movieapp.mvp.MainPresenter;
import com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders.MovieViewHolder;
import com.example.intern.movieapp.mvp.data.FavoritesDbHelper;

public class MainActivity extends AppCompatActivity implements MVP_API.ViewOperations{
    private MovieViewAdapter mAdapter;
    private static MVP_API.PViewOperations mPresenter;
    private EndlessRecyclerViewScrollListener scrollListener;
    private RecyclerView mList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupMVP();
    }

    private void setupViews() {
        mAdapter = new MovieViewAdapter();
        mList = findViewById(R.id.movie_list);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mList.setLayoutManager(layoutManager);
        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.onLoadMore(page, totalItemsCount, view);
            }
        };
        mList.addOnScrollListener(scrollListener);
        mList.setAdapter(mAdapter);
        mList.setItemAnimator(new DefaultItemAnimator());
    }

    private void setupMVP() {
        MainPresenter presenter = new MainPresenter(this);
        MainModel model = new MainModel(presenter);
        presenter.setModel(model);
        mPresenter = presenter;
    }

    @Override
    public Context getActivityContext() {
        return this;
    }

    @Override
    public Context getAppContext() {
        return getApplicationContext();
    }

    public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewHolder> {

        @NonNull
        @Override
        public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return mPresenter.createViewHolder(viewGroup, i);
        }

        @Override
        public void onBindViewHolder(@NonNull final MovieViewHolder movieViewHolder, int i) {
            mPresenter.bindViewHolder(movieViewHolder, i);
            movieViewHolder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(mPresenter.setOnClickListener(movieViewHolder, movieViewHolder.getAdapterPosition()));

                }
            });

        }

        @Override
        public int getItemCount() {
            return mPresenter.getItemCount();
        }

    }

    @Override
    public void showToast(Toast toast) {
        toast.show();
    }

    @Override
    public void notifyDataSetChanged() {
        mList.post(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
