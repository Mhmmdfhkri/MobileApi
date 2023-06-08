package com.example.final_mobile;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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
                selectTab(tab_movie);
                replaceFragment(new MovieFragment());
            }
        });

        tab_television.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTab(tab_television);
                replaceFragment(new TelevisionFragment());
            }
        });

        tab_favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTab(tab_favorites);
                replaceFragment(new FavoritesFragment());
            }
        });
    }

    private void selectTab(ImageView tab) {
        tab_movie.setSelected(false);
        tab_television.setSelected(false);
        tab_favorites.setSelected(false);
        tab.setSelected(true);

        int selectedColor = getResources().getColor(androidx.cardview.R.color.cardview_dark_background); // Warna ungu yang diinginkan
        int unselectedColor = getResources().getColor(R.color.black); // Warna default

        tab_movie.setColorFilter(unselectedColor);
        tab_television.setColorFilter(unselectedColor);
        tab_favorites.setColorFilter(unselectedColor);
        tab.setColorFilter(selectedColor);
    }

    private void replaceFragment(Fragment fragment) {
        new LoadFragmentTask().execute(fragment);
    }

    private class LoadFragmentTask extends AsyncTask<Fragment, Void, Void> {

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Fragment... fragments) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frame_container, fragments[0]);
            fragmentTransaction.commit();

            // Menunda eksekusi pembaruan UI selama 0.2 detik (200 milidetik)
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
