package com.rad5.fechjsonfromapi;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class apiUrl {

    private static final String baseUrl = "https://www.googleapis.com/books/v1/volumes/";
    private static final String QUERY_PERIMETER_KEY = "q";
    private static final String KEY  = "Key";
    private static final String API_KEY= "AIzaSyAYzkoKCej5X-r0sJAMvK_6esXBeMAFheg";


    public static URL buildUrl(String title) {

        URL uri = null;
        Uri  jsonUri = Uri.parse(baseUrl)
                .buildUpon()
                .appendQueryParameter(QUERY_PERIMETER_KEY,title)
                .appendQueryParameter(KEY, API_KEY)
                .build();
        try {
            uri = new URL(jsonUri.toString());
        } catch (Exception e) {
            return uri;

        }
        return uri;
    }

    public static String getJson(URL url) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try {
            InputStream stream = connection.getInputStream();
            Scanner scanner = new Scanner(stream);
            scanner.useDelimiter("\\A");

            boolean hasNext = scanner.hasNext();
            if (hasNext){
                return scanner.next();
            }
            else {
                return null;
            }
        }catch (Exception e){
            Log.d(" Error =  ",e.toString());
            return null;
        }
        finally {
            connection.disconnect();
        }


    }

    public static ArrayList<Book> getBooksFromJson(String json) {
        final String ID = "id";
        final String TITLE = "title";
        final String SUBTITLE = "subtitle";
        final String AUTHORS = "authors";
        final String PUBLISHER = "publisher";
        final String PUBLISHED_DATE="publishedDate";
        final String ITEMS = "items";
        final String VOLUMEINFO = "volumeInfo";

        ArrayList<Book> books = new ArrayList<Book>();
        try {
            JSONObject jsonBooks = new JSONObject(json);
            JSONArray arrayBooks = jsonBooks.getJSONArray(ITEMS);
            int numberOfBooks = arrayBooks.length();

            for (int i =0; i<numberOfBooks;i++){
                JSONObject bookJSON = arrayBooks.getJSONObject(i);
                JSONObject volumeInfoJSON = bookJSON.getJSONObject(VOLUMEINFO);
                int authorNum = volumeInfoJSON.getJSONArray(AUTHORS).length();
                String[] authors = new String[authorNum];
                for (int j=0; j<authorNum;j++) {
                    authors[j] = volumeInfoJSON.getJSONArray(AUTHORS).get(j).toString();
                }
                Book book = new Book(
                        bookJSON.getString(ID),
                        volumeInfoJSON.getString(TITLE),
                        (volumeInfoJSON.isNull(SUBTITLE)?"":volumeInfoJSON.getString(SUBTITLE)),
                        authors,
                        volumeInfoJSON.getString(PUBLISHER),
                        volumeInfoJSON.getString(PUBLISHED_DATE));
                books.add(book);

            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }



        return books;
    }

}
