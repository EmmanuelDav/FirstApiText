package com.rad5.fechjsonfromapi;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.rad5.fechjsonfromapi.databinding.BookDetailsBinding;

public class BookDetails  extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_details);
        Book book = getIntent().getParcelableExtra("Book");
         BookDetailsBinding bookDetails = DataBindingUtil.setContentView(BookDetails.this,
                R.layout.book_details);
         bookDetails.setBook(book);
    }
}
