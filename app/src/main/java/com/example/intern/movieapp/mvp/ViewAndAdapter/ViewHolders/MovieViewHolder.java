package com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.intern.movieapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        setupView(itemView);
    }

    private void setupView(View view) {
        textView = view.findViewById(R.id.textViewSample);
    }
}