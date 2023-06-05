package com.example.final_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.transition.FragmentTransitionSupport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.final_mobile.fragments.FavoritesFragment;
import com.example.final_mobile.fragments.MovieFragment;
import com.example.final_mobile.fragments.televisionFragment;

public class MainActivity extends AppCompatActivity {

    ImageView tab_movie, tab_favorites, tab_television;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab_movie = findViewById(R.id.tab_movies);
        tab_television = findViewById(R.id.tab_television);
        tab_favorites = findViewById(R.id.tab_favorites);


        tab_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MovieFragment());
            }
        });

        tab_television.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new televisionFragment());
            }
        });

        tab_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new FavoritesFragment());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }
}