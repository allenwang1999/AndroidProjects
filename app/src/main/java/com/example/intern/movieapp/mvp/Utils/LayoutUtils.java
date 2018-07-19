package com.example.intern.movieapp.mvp.Utils;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

import com.example.intern.movieapp.R;

public class LayoutUtils extends AppCompatActivity {
    private static final int POSTER_WIDTH = 342;
    public static int numberOfColumns(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels;
        int numColumns = (int) (dpWidth / POSTER_WIDTH);
        return numColumns;

    }

    public static int getPadding(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels;
        int numColumns = (int) (dpWidth / POSTER_WIDTH);
        int remainingWidth = (int) (dpWidth % POSTER_WIDTH);
        int padding = remainingWidth / numColumns;
        return padding;
    }
}
