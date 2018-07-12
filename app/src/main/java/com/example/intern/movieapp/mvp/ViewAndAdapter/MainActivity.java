package com.example.intern.movieapp.mvp.ViewAndAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.intern.movieapp.R;
import com.example.intern.movieapp.mvp.MVPAPI;
import com.example.intern.movieapp.mvp.MainModel;
import com.example.intern.movieapp.mvp.MainPresenter;
import com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders.MovieViewHolder;

public class MainActivity extends AppCompatActivity implements MVPAPI.ViewOperations{
    private MovieViewAdapter mAdapter;
    private static MVPAPI.PViewOperations mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupMVP();
    }

    private void setupViews() {
        mAdapter = new MovieViewAdapter();
        RecyclerView mList = (RecyclerView) findViewById(R.id.movie_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setLayoutManager(layoutManager);
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
        public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
            mPresenter.bindViewHolder(movieViewHolder, i);
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
        mAdapter.notifyDataSetChanged();
    }
}
