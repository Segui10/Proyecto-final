package com.herprogramacion.restaurantericoparico.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by 1DAW on 21/03/17.
 */

public class BookClient  {
    private static final String API_BASE_URL = "https://api.themoviedb.org/3/";
    private AsyncHttpClient client;

    public BookClient() {
        this.client = new AsyncHttpClient();
    }

    private String getApiUrl(String relativeUrl) {
        return API_BASE_URL + relativeUrl;
    }

    // Method for accessing the search API
    public void getBooks(final String query, JsonHttpResponseHandler handler) {
        try {
            String url = getApiUrl("search/movie?api_key=2c5b24e9895c627d2e1a2cdaf1c2dbe5&language=en-US&"+"query=");
            client.get(url + URLEncoder.encode(query, "utf-8"), handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    public void getBooks2(final String query, JsonHttpResponseHandler handler) {
        try {
            String url = getApiUrl("movie/upcoming?api_key=2c5b24e9895c627d2e1a2cdaf1c2dbe5&language=en-US&page=1");
            client.get(url + URLEncoder.encode(query, "utf-8"), handler);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    // Method for accessing books API to get publisher and no. of pages in a book.
    public void getExtraBookDetails(String openLibraryId, JsonHttpResponseHandler handler) {
        String url = getApiUrl("movie/");
        client.get(url + openLibraryId + "?api_key=2c5b24e9895c627d2e1a2cdaf1c2dbe5&language=en-US", handler);
    }
}
