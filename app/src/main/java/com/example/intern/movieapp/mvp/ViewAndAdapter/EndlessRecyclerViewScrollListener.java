package com.example.intern.movieapp.mvp.ViewAndAdapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.intern.movieapp.mvp.Utils.LayoutUtils;

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int visibleThreshold = 15;
    private int currentPage = 1;
    private int previousItemCount = 0;
    private boolean loading = true;
    private int startingPageIndex = 1;
    RecyclerView.LayoutManager mLayoutManager;
    public EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        int lastVisibleItemPosition = ((GridLayoutManager) mLayoutManager).findLastVisibleItemPosition();
        int totalItemCount = mLayoutManager.getItemCount();

        if(totalItemCount < previousItemCount) {
            this.currentPage = this.startingPageIndex;
            this.previousItemCount = totalItemCount;
            if(totalItemCount == 0) {
                this.loading = true;
            }
        }

        if(loading && (totalItemCount > previousItemCount)) {
            loading = false;
            previousItemCount = totalItemCount;
        }

        if(!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, recyclerView);
            loading = true;
        }
    }

    public void resetState() {
        this.currentPage = this.startingPageIndex;
        this.previousItemCount = 0;
        this.loading = true;
    }

    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);
}
