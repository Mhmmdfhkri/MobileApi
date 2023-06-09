package com.example.final_mobile.DetailActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.final_mobile.Class.Movie;
import com.example.final_mobile.R;
import com.example.final_mobile.fragments.DetailFragment;

public class MovieDetailActivity extends AppCompatActivity {


    public static final String ARG_MOVIE = "movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        if (getIntent() != null) {
            Movie movie = getIntent().getParcelableExtra(ARG_MOVIE);
            showDetailFragment(movie);
        }
    }

    private void showDetailFragment(Movie movie) {
        DetailFragment fragment = DetailFragment.newInstance(movie);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
