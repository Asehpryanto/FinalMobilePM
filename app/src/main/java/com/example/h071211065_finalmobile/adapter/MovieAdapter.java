package com.example.h071211065_finalmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211065_finalmobile.R;
import com.example.h071211065_finalmobile.activity.DetailActivity;
import com.example.h071211065_finalmobile.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;

    public MovieAdapter() {
        this.movies = new ArrayList<>();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_card, parent, false);
        return new MovieViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView yearTextView;
        private Context context;

        public MovieViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
            posterImageView = itemView.findViewById(R.id.imageView);
            titleTextView = itemView.findViewById(R.id.tvNamaFilm);
            yearTextView = itemView.findViewById(R.id.tvTahun);
        }

        public void bind(Movie movie) {
            String title = movie.getTitle();
            String year = movie.getReleaseDate().substring(0,4);
            String poster = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + movie.getPosterPath();
            titleTextView.setText(title);
            yearTextView.setText(year);
            Glide.with(context)
                    .load(poster)
                    .into(posterImageView);
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("movie", movie);
                context.startActivity(intent);
            });
        }
    }
}
