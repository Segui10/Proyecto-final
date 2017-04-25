package com.herprogramacion.restaurantericoparico.models;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by 1DAW on 21/03/17.
 */

public class Book implements Serializable {
    private String openLibraryId;
    private String author;
    private String title;
    private String img;
    private String stars;
    private String popu;
    private String Rdate;
    private String testid;


    public String getStars() {return stars;}
    public String gettestid() {return testid;}
    public String getPopu() {return popu;}
    public String getRdate() {return Rdate;}
    public String getOpenLibraryId() {
        return openLibraryId;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getImg(){return "https://image.tmdb.org/t/p/w185_and_h278_bestv2"+img;}


    // Get large sized book cover from covers API


    // Returns a Book given the expected JSON
    public static Book fromJson(JSONObject jsonObject) {
        Book book = new Book();
        try {
            // Deserialize json into object fields
            // Check if a cover edition is available
            if (jsonObject.has("id")) {
                book.openLibraryId = jsonObject.getString("id");
            } else if(jsonObject.has("id")) {
                final JSONArray ids = jsonObject.getJSONArray("id");
                book.openLibraryId = ids.getString(0);
            }
            book.title = jsonObject.has("id") ? jsonObject.getString("id") : "";
            book.title = jsonObject.has("original_title") ? jsonObject.getString("original_title") : "";
            book.author = getAuthor(jsonObject);
            book.img=jsonObject.has("poster_path")? jsonObject.getString("poster_path"):"";
            book.stars=jsonObject.has("vote_average")? jsonObject.getString("vote_average"):"";
            book.popu=jsonObject.has("vote_count")? jsonObject.getString("vote_count"):"";
            book.Rdate=jsonObject.has("release_date")? jsonObject.getString("release_date"):"";
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        // Return new object
        return book;
    }

    // Return comma separated author list when there is more than one author
    private static String getAuthor(final JSONObject jsonObject) {
        try {
            final JSONArray authors = jsonObject.getJSONArray("production_companies");
            int numAuthors = authors.length();
            final String[] authorStrings = new String[numAuthors];
            for (int i = 0; i < numAuthors; ++i) {
                authorStrings[i] = authors.getString(i);
            }
            return TextUtils.join(", ", authorStrings);
        } catch (JSONException e) {
            return "";
        }
    }

    // Decodes array of book json results into business model objects
    public static ArrayList<Book> fromJson(JSONArray jsonArray) {
        ArrayList<Book> books = new ArrayList<Book>(jsonArray.length());
        // Process each result in json array, decode and convert to business
        // object
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject bookJson = null;
            try {
                bookJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Book book = Book.fromJson(bookJson);
            if (book != null) {
                books.add(book);
            }
        }
        return books;
    }
}