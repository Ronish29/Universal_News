package com.example.universalnews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class News_Gener_Adapter extends RecyclerView.Adapter<News_Gener_Adapter.ViewHolder> {

    private List<MyData> mDataset;

    private List<Genre> genres= new ArrayList<>();


    public News_Gener_Adapter(List<Genre> genres) {
        this.genres = genres;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_geners_item, parent, false);
        String[] genreNames = {"Business", "Entertainment", "Health", "Science", "Sports", "Technology"};

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Genre genre = genres.get(position);
        holder.checkbox.setChecked(genre.isChecked());
        holder.text.setText(genre.getName());
    }

    @Override
    public int getItemCount() {
        return genres.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkbox;
        public TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.genre_checkbox);
            text = itemView.findViewById(R.id.genre_name);

            // Set click listener on checkbox
            checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the clicked genre
                    Genre clickedGenre = genres.get(getAdapterPosition());

                    // Move the clicked genre to the top of the list
                    genres.remove(getAdapterPosition());
                    genres.add(0, clickedGenre);

                    // Sort the list alphabetically
                    Collections.sort(genres);
                    clickedGenre.setChecked(checkbox.isChecked());

                    // Notify the adapter of the changes
                    notifyDataSetChanged();
                }
            });
        }
    }
}

