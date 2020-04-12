package com.rad5.fechjsonfromapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {


    private ProgressBar mProgresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgresBar = findViewById(R.id.progressBar);

        try {
            URL bookUrl = apiUrl.buildUrl("cooking");
            new BookQueryClass().execute(bookUrl);
        } catch (Exception e) {
            Log.d("Error E = ", e.toString());
        }
    }

    public class BookQueryClass extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchurl = urls[0];
            String result = null;
            try {
                result = apiUrl.getJson(searchurl);


            } catch (Exception e) {
                Log.d("Exception error e =", e.toString());

            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView response = findViewById(R.id.response);
            TextView error = findViewById(R.id.error);
            mProgresBar.setVisibility(View.INVISIBLE);
            if (s != null) {
                error.setVisibility(View.INVISIBLE);
                response.setVisibility(View.VISIBLE);

            }else {
                response.setVisibility(View.INVISIBLE);
                error.setVisibility(View.VISIBLE);
            }
            response.setText(s);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgresBar.setVisibility(View.VISIBLE);
        }
    }

}
