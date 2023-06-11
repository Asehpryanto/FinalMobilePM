package com.example.h071211065_finalmobile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.h071211065_finalmobile.R;
import com.example.h071211065_finalmobile.database.DatabaseHelper;
import com.example.h071211065_finalmobile.model.Favorite;
import com.example.h071211065_finalmobile.model.Movie;
import com.example.h071211065_finalmobile.model.TVShow;

public class DetailActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    private ImageView backdropImageView, backButton, favoriteButton, posterImageView;
    private TextView titleTextView, ratingTextView, synopsisTextView;
    boolean favorite = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        backdropImageView = findViewById(R.id.iv_wallpaper);
        backButton = findViewById(R.id.btn_back);
        favoriteButton = findViewById(R.id.btn_favorite);
        posterImageView = findViewById(R.id.imgDetailMovie);
        titleTextView = findViewById(R.id.judul);
        ratingTextView = findViewById(R.id.vote);
        synopsisTextView = findViewById(R.id.sinopsis);
        dbHelper = new DatabaseHelper(this);

        Intent intent = getIntent();
        if (intent.getParcelableExtra("movie") != null) {
            Movie movie = intent.getParcelableExtra("movie");
            handleFilmDetails(movie.getTitle(), String.valueOf(movie.getVoteAverage()), movie.getOverview(), movie.getPosterPath(), movie.getBackdropUrl(), movie.getId(), movie.getReleaseDate(), R.drawable.baseline_movie);
        } else if (intent.getParcelableExtra("tvShow") != null) {
            TVShow show = intent.getParcelableExtra("tvShow");
            handleFilmDetails(show.getName(), String.valueOf(show.getVoteAverage()), show.getOverview(), show.getPosterPath(), show.getBackdropPath(), show.getId(), show.getName(), R.drawable.baseline_tv);
        } else if (intent.getParcelableExtra("favorite") != null) {
            Favorite favorite = intent.getParcelableExtra("favorite");

            handleFilmDetails(favorite.getTitle(), String.valueOf(favorite.getVoteAverage()), favorite.getOverview(), favorite.getPosterPath(), favorite.getBackdropPath(), favorite.getId(), favorite.getTitle(), R.drawable.ic_favorite);
        }

        backButton.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void handleFilmDetails(String title, String voteAverage, String overview, String posterPath, String backdropPath, int id, String releaseDate, int type) {
        String posterUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + posterPath;
        String backdropUrl = "https://image.tmdb.org/t/p/w300_and_h450_bestv2/" + backdropPath;
        titleTextView.setText(title);
        ratingTextView.setText(voteAverage);
        Glide.with(this).load(posterUrl).into(posterImageView);
        Glide.with(this).load(backdropUrl).into(backdropImageView);
        synopsisTextView.setText(overview);

        if (dbHelper.isMovieInFavorites(title)) {
            favoriteButton.setImageResource(R.drawable.ic_favorite);
        } else {
            favoriteButton.setImageResource(R.drawable.baseline_favorite_border);
        }

        favoriteButton.setOnClickListener(view -> {
            if (!dbHelper.isMovieInFavorites(title)) {
                favoriteButton.setImageResource(R.drawable.ic_favorite);
                favorite = true;
                addMovieToFavorites(id, overview, posterUrl, releaseDate, title, Double.parseDouble(voteAverage), backdropUrl);
            } else {
                favoriteButton.setImageResource(R.drawable.baseline_favorite_border);
                favorite = false;
                deleteMovieFromFavorites(title);
            }
        });
    }
    private void addMovieToFavorites(int id, String overview, String posterUrl, String releaseDate, String title, double voteAverage, String backdropUrl) {
        Movie movie = new Movie(id, overview, posterUrl, releaseDate, title, voteAverage, backdropUrl);
        long result = dbHelper.insertMovie(movie);
        if (result != -1) {
            Toast.makeText(this, "Success added to favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to add to favorites", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteMovieFromFavorites(String nama) {
        long result = dbHelper.deleteMovie(nama);
        if (result != -1) {
            Toast.makeText(this, "Success deleted from favorites", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Failed to delete from favorites", Toast.LENGTH_SHORT).show();
        }
    }
}