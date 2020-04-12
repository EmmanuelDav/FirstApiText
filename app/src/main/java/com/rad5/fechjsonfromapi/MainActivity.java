package com.rad5.fechjsonfromapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.net.URL;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            URL bookUrl = apiUrl.buildUrl("cooking");
           new  BookQueryClass().execute(bookUrl);
        }catch (Exception e){
            Log.d("Error E = " ,e.toString());
        }
    }

    public class BookQueryClass extends AsyncTask<URL,Void,String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchurl =  urls[0];
            String result = null;
            try{
                result = apiUrl.getJson(searchurl);


            }catch ( Exception e){
                Log.d("Exception error e =" ,e.toString());

            }
            return  result;
        }

        @Override
        protected void onPostExecute(String s) {
            TextView response = findViewById(R.id.response);
            response.setText(s);

        }
    }

}
