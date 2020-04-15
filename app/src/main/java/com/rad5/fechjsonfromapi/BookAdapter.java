package com.rad5.fechjsonfromapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    ArrayList<Book>mBooks;


    public BookAdapter(ArrayList<Book> books) {
        mBooks = books;
    }

    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.book_list,parent,false);
        return new BookAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Book mbook = mBooks.get(position);
        holder.bind(mbook);

    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Publishers,Author,PublishersDate,title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Publishers = itemView.findViewById(R.id.tv_publisher);
            Author = itemView.findViewById(R.id.tv_authorsize);
            PublishersDate = itemView.findViewById(R.id.tv_publishDate);
            title = itemView.findViewById(R.id.tv_title);

        }

        public void bind (Book book) {
            title.setText(book.getTitle());
            String authors="";
            int i=0;
            for (String author:book.getAuthors()) {
                authors+=author;
                i++;
                if(i<book.getAuthors().length) {
                    authors+=", ";
                }
            }
            Author.setText(authors);
            PublishersDate.setText(book.getPublishedDate());
            Publishers.setText(book.getPublisher());

        }

    }
}
