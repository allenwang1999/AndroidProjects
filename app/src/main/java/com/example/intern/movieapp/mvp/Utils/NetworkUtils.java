package com.example.intern.movieapp.mvp.Utils;

import android.content.Context;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String BASE_URL_DISCOVER_MOVIES = "https://api.themoviedb.org/3/discover/movie";
    private static final String BASE_URL_IMAGES = "https://image.tmdb.org/t/p/";


    //Query keys
    private final static String apiKey = "api_key";
    private final static String language = "language";
    private final static String sort = "sort_by";
    private final static String includeAdult = "include_adult";
    private final static String includeVideo = "include_video";
    private final static String page = "page";

    //Query params
    private final static String API_KEY="2092cf092c4fc7a2ebee85e8c66a1af8";
    private final static String LANGUAGE="en_US";
    private final static String SORT = "popularity.desc";
    private final static String INCLUDE_ADULT = "false";
    private final static String INCLUDE_VIDEO = "false";

    public static URL getMoviesURL(int page) {
        return buildUrl(page);
    }
    private static URL buildUrl(int pageNum) {
        Uri builtUri = Uri.parse(BASE_URL_DISCOVER_MOVIES).buildUpon()
                .appendQueryParameter(apiKey, API_KEY)
                .appendQueryParameter(language, LANGUAGE)
                .appendQueryParameter(sort, SORT)
                .appendQueryParameter(includeAdult, INCLUDE_ADULT)
                .appendQueryParameter(includeVideo, INCLUDE_VIDEO)
                .appendQueryParameter(page, Integer.toString(pageNum))
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static URL buildImageUrl(String filePath, String size) {
        URL url = null;
        if(filePath != null) {
            Uri builtUri = Uri.parse(BASE_URL_IMAGES).buildUpon()
                    .appendPath(size)
                    .appendPath(filePath.substring(1))
                    .build();
            try {
                url = new URL(builtUri.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return url;
    }

    public static String getResponseFromUrl(URL url) throws IOException {
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if(hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
