package com.example.final_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.final_mobile.fragments.FavoritesFragment;
import com.example.final_mobile.fragments.MovieFragment;
import com.example.final_mobile.fragments.TelevisionFragment;


public class MainActivity extends AppCompatActivity {

    private ImageView tab_movie, tab_favorites, tab_television;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab_movie = findViewById(R.id.tab_movies);
        tab_television = findViewById(R.id.tab_television);
        tab_favorites = findViewById(R.id.tab_favorites);
        progressBar = findViewById(R.id.progress_bar);

        replaceFragment(new MovieFragment());

        tab_movie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new MovieFragment());
            }
        });

        tab_television.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFragment(new TelevisionFragment());
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

        // Memulai background thread untuk menampilkan ProgressBar
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute();
    }

    private class BackgroundTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressBar.setVisibility(View.GONE);
        }
    }
}
