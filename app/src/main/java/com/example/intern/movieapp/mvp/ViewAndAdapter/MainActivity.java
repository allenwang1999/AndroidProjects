package com.example.intern.movieapp.mvp.ViewAndAdapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.intern.movieapp.R;
import com.example.intern.movieapp.mvp.MVP_API;
import com.example.intern.movieapp.mvp.MainModel;
import com.example.intern.movieapp.mvp.MainPresenter;
import com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders.MovieViewHolder;

public class MainActivity extends AppCompatActivity implements MVP_API.ViewOperations{
    private MovieViewAdapter mAdapter;
    private static MVP_API.PViewOperations mPresenter;
    private EndlessRecyclerViewScrollListener mScrollListener;
    private GridLayoutManager mLayoutManager;
    private RecyclerView mList;
    private static final String RECYCLER_BUNDLE_KEY = "MainActivity.recycler.layout";
    public Parcelable mSavedRecyclerLayoutState;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSavedRecyclerLayoutState = savedInstanceState.getParcelable(RECYCLER_BUNDLE_KEY);
        setContentView(R.layout.activity_main);
        setupViews();
        setupMVP();
    }

    //TODO Add the onSaveInstanceState stuff to the presenter's aSyncTask.

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(RECYCLER_BUNDLE_KEY, mList.getLayoutManager().onSaveInstanceState());
    }


    private void setupViews() {
        mAdapter = new MovieViewAdapter();
        mList = findViewById(R.id.movie_list);
        mLayoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        mList.setLayoutManager(mLayoutManager);
        mScrollListener = new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                mPresenter.onLoadMore(page, totalItemsCount, view);
            }
        };
        mList.addOnScrollListener(mScrollListener);
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
                    startActivityForResult(mPresenter.setOnClickListener(movieViewHolder, movieViewHolder.getAdapterPosition()), 1);
                }
            });

        }

        @Override
        public int getItemCount() {
            return mPresenter.getItemCount();
        }

        public void clear() {
            while(getItemCount() != 0) {
                int itemCount = getItemCount();
                mPresenter.clearViews(itemCount);
                notifyItemRangeRemoved(0, itemCount);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movieview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.favoriteView) {
            mList.removeOnScrollListener(mScrollListener);
            mAdapter.clear();
            mPresenter.showFavoriteViews();
        } else if(itemId == R.id.popularView) {
            setupViews();
            setupMVP();
        }
        return super.onOptionsItemSelected(item);
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
