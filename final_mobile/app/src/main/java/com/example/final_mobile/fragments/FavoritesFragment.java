package com.example.final_mobile.fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_mobile.Adapter.FavoriteAdapter;
import com.example.final_mobile.Database.DatabaseHelperMovie;
import com.example.final_mobile.Model.FavoriteItem;
import com.example.final_mobile.R;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

    private View view;
    private RecyclerView recyclerView;
    private FavoriteAdapter favoriteAdapter;
    private List<FavoriteItem> favoriteList;
    private DatabaseHelperMovie databaseHelperMovie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_favorites, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        favoriteList = new ArrayList<>();
        favoriteAdapter = new FavoriteAdapter(getActivity(), favoriteList);
        recyclerView.setAdapter(favoriteAdapter);

        Context context = requireContext();
        databaseHelperMovie = new DatabaseHelperMovie(context);

        LoadFavoritesTask loadFavoritesTask = new LoadFavoritesTask();
        loadFavoritesTask.execute();

        return view;
    }

    private class LoadFavoritesTask extends AsyncTask<Void, Void, List<FavoriteItem>> {
        @Override
        protected List<FavoriteItem> doInBackground(Void... voids) {
            // Panggil metode getFavoriteMovies atau getFavoriteTelevision sesuai kebutuhan
            return databaseHelperMovie.getFavoriteMovies();
        }

        @Override
        protected void onPostExecute(List<FavoriteItem> favorites) {
            favoriteList.clear();
            favoriteList.addAll(favorites);
            favoriteAdapter.notifyDataSetChanged();
        }
    }
}
