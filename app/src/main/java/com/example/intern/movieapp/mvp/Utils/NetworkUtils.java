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
    static final String BASE_URL_DISCOVER_MOVIES = "https://api.themoviedb.org/3/discover/movie";
    static final String BASE_URL_IMAGES = "https://image.tmdb.org/t/p/";


    //Query keys
    final static String apiKey = "api_key";
    final static String language = "language";
    final static String sort = "sort_by";
    final static String includeAdult = "include_adult";
    final static String includeVideo = "include_video";
    final static String page = "page";

    //Query params
    final static String API_KEY="2092cf092c4fc7a2ebee85e8c66a1af8";
    final static String LANGUAGE="en_US";
    final static String SORT = "popularity.desc";
    final static String INCLUDE_ADULT = "false";
    final static String INCLUDE_VIDEO = "false";
    final static String PAGE = "1";

    public static URL getMoviesURL() {
        return buildUrl();
    }
    public static URL buildUrl() {
        Uri builtUri = Uri.parse(BASE_URL_DISCOVER_MOVIES).buildUpon()
                .appendQueryParameter(apiKey, API_KEY)
                .appendQueryParameter(language, LANGUAGE)
                .appendQueryParameter(sort, SORT)
                .appendQueryParameter(includeAdult, INCLUDE_ADULT)
                .appendQueryParameter(includeVideo, INCLUDE_VIDEO)
                .appendQueryParameter(page, PAGE)
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
        Uri builtUri = Uri.parse(BASE_URL_IMAGES).buildUpon()
                .appendPath(size)
                .appendPath(filePath.substring(1))
                .build();
        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
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
