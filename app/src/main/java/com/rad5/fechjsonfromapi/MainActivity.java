package com.rad5.fechjsonfromapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {


    private ProgressBar mProgresBar;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgresBar = findViewById(R.id.progressBar);
        mRecyclerView = findViewById(R.id.RecyclerView);

        try {
            URL bookUrl = apiUrl.buildUrl("love");
            new BookQueryClass().execute(bookUrl);
        } catch (Exception e) {
            Log.d("Error E = ", e.toString());
        }

        LinearLayoutManager BookManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(BookManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final MenuItem searchMenu = menu.findItem(R.id.search_view);
        //  SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchMenu);
        final SearchView searchView = (SearchView) searchMenu.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        try {
            URL bookUrl = apiUrl.buildUrl(s);
            new BookQueryClass().execute(bookUrl);
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
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

            TextView error = findViewById(R.id.error);
            mProgresBar.setVisibility(View.INVISIBLE);
            if (s != null) {
                error.setVisibility(View.INVISIBLE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mRecyclerView.setVisibility(View.INVISIBLE);
                error.setVisibility(View.VISIBLE);
            }
            ArrayList<Book> books = apiUrl.getBooksFromJson(s);
            BookAdapter adapter = new BookAdapter(books);
            mRecyclerView.setAdapter(adapter);


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgresBar.setVisibility(View.VISIBLE);
        }
    }

}
