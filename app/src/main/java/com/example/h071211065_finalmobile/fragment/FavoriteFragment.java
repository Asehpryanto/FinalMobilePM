package com.example.h071211065_finalmobile.fragment;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.h071211065_finalmobile.R;
import com.example.h071211065_finalmobile.adapter.FavoriteAdapter;
import com.example.h071211065_finalmobile.database.DatabaseContract;
import com.example.h071211065_finalmobile.database.DatabaseHelper;
import com.example.h071211065_finalmobile.model.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {
    private RecyclerView rvFavorites;
    private FavoriteAdapter favoriteAdapter;
    private DatabaseContract database;

    public FavoriteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        rvFavorites = view.findViewById(R.id.rvMovie);
        rvFavorites.setLayoutManager(new LinearLayoutManager(getContext()));
        List<Favorite> favoriteList = getAllMoviesFromDatabase();
        favoriteAdapter = new FavoriteAdapter(favoriteList);
        rvFavorites.setAdapter(favoriteAdapter);
        return view;
    }

    private List<Favorite> getAllMoviesFromDatabase() {
        List<Favorite> favoriteList = new ArrayList<>();
        DatabaseHelper movieHelper = new DatabaseHelper(getActivity());
        Cursor cursor = movieHelper.getAllMovies();

        if (cursor != null && cursor.moveToFirst()) {

            int idColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry._ID);
            int titleColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_TITLE);
            int releaseDateColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_RELEASE_DATE);
            int overviewColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_OVERVIEW);
            int posterUrlColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_POSTER_URL);
            int backdropUrlColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_BACKDROP_URL);
            int voteAverageColumnIndex = cursor.getColumnIndex(DatabaseContract.DatabaseEntry.COLUMN_VOTE_AVERAGE);

            do {
                int id = (idColumnIndex != -1) ? cursor.getInt(idColumnIndex) : -1;
                String title = (titleColumnIndex != -1) ? cursor.getString(titleColumnIndex) : null;
                String releaseDate = (releaseDateColumnIndex != -1) ? cursor.getString(releaseDateColumnIndex) : null;
                String overview = (overviewColumnIndex != -1) ? cursor.getString(overviewColumnIndex) : null;
                String posterUrl = (posterUrlColumnIndex != -1) ? cursor.getString(posterUrlColumnIndex) : null;
                String backdropUrl = (backdropUrlColumnIndex != -1) ? cursor.getString(backdropUrlColumnIndex) : null;
                double voteAverage = (voteAverageColumnIndex != -1) ? cursor.getDouble(voteAverageColumnIndex) : 0.0;

                Favorite favorite = new Favorite(id, overview, posterUrl, releaseDate, title, voteAverage, backdropUrl);
                favoriteList.add(favorite);
            } while (cursor.moveToNext());

        }

        if (cursor != null) {
            cursor.close();
        }

        return favoriteList;
    }
}
