package com.rad5.fechjsonfromapi;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class apiUrl {

    public static final String baseUrl = "https://www.googleapis.com/books/v1/volumes/";

    public static URL buildUrl(String title) {
        String url = baseUrl + "?q=" + title;
        URL uri = null;
        try {
            uri = new URL(url);

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
