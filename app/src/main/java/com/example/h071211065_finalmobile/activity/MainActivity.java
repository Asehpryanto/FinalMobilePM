package com.example.h071211065_finalmobile.activity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.h071211065_finalmobile.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.h071211065_finalmobile.fragment.FavoriteFragment;
import com.example.h071211065_finalmobile.fragment.MovieFragment;
import com.example.h071211065_finalmobile.fragment.TVShowFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView movie_iv, series_iv, favorites_iv;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        movie_iv = findViewById(R.id.btn_movie);
        series_iv = findViewById(R.id.btn_tv);
        favorites_iv = findViewById(R.id.btn_favorite);

        movie_iv.setOnClickListener(this);
        series_iv.setOnClickListener(this);
        favorites_iv.setOnClickListener(this);

        showFragment(new MovieFragment());
    }

    public void onClick(View v) {
        final Drawable drawable;
        Fragment fragment;
        if (v == movie_iv) {
            drawable = movie_iv.getDrawable();
            fragment = new MovieFragment();
        } else if (v == series_iv) {
            drawable = series_iv.getDrawable();
            fragment = new TVShowFragment();
        } else if (v == favorites_iv) {
            drawable = favorites_iv.getDrawable();
            fragment = new FavoriteFragment();
        } else {
            return;
        }
        if (drawable != null) {
            drawable.setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_IN);
            ((ImageView) v).setImageDrawable(drawable);

            new Handler().postDelayed(() -> {
                drawable.clearColorFilter();
                ((ImageView) v).setImageDrawable(drawable);
            }, 200);

            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

            showFragment(fragment);
        }
    }

    private void showFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(null)
                .commit();
    }
}