package com.rad5.fechjsonfromapi;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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

}
