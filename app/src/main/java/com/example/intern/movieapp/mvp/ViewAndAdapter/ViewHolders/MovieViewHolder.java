package com.example.intern.movieapp.mvp.ViewAndAdapter.ViewHolders;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.intern.movieapp.R;

public class MovieViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public ConstraintLayout constraintLayout;

    public MovieViewHolder(@NonNull View itemView) {
        super(itemView);
        setupView(itemView);
    }

    private void setupView(View view) {
        constraintLayout = view.findViewById(R.id.mainlayout);
        imageView = view.findViewById(R.id.imageViewMainPoster);
    }


}