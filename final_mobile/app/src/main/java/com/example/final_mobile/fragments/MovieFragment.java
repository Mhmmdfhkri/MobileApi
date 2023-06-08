package com.example.final_mobile.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_mobile.Adapter.MovieAdapter;
import com.example.final_mobile.Class.Movie;
import com.example.final_mobile.DetailActivity.MovieDetailActivity;
import com.example.final_mobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;

    private List<Movie> movieList = new ArrayList<>();
    private TextView internetConection;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        internetConection = view.findViewById(R.id.tv_internetConection);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2)); // Mengatur jumlah kolom menjadi 2

        movieAdapter = new MovieAdapter(movieList, new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Movie movie) {
                showDetailActivity(movie);
            }
        });
        recyclerView.setAdapter(movieAdapter);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Panggil fungsi untuk mengambil data film dari API dan menampilkannya
        fetchMovies();
    }

    private void fetchMovies() {
        // Periksa koneksi internet sebelum melakukan permintaan API
        if (!isNetworkAvailable()) {
            internetConection.setVisibility(View.VISIBLE);
            return;
        }

        // Menggunakan library Volley untuk mengambil data film dari API
        RequestQueue queue = Volley.newRequestQueue(requireContext());
        String baseUrl = "https://api.themoviedb.org/3/movie/now_playing"; // Base URL API The Movie Database
        String apiKey = "5fcb44f5991c765d4bfabcd479b852bf"; // API key yang diberikan
        String url = baseUrl + "?api_key=" + apiKey;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parsing data JSON menjadi objek Movie
                            JSONArray results = response.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject movieObject = results.getJSONObject(i);
                                int id = movieObject.getInt("id");
                                String title = movieObject.getString("title");
                                String posterPath = movieObject.getString("poster_path");
                                String releaseDate = movieObject.getString("release_date");
                                double voteAverage = movieObject.getDouble("vote_average");
                                String overview = movieObject.getString("overview");
                                String backdropPath = movieObject.getString("backdrop_path");
                                Movie movie = new Movie(id, title, posterPath, releaseDate, voteAverage, overview, backdropPath);
                                movieList.add(movie);
                            }
                            movieAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Penanganan kesalahan jika permintaan gagal
                        Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        queue.add(request);
    }

    private void showDetailActivity(Movie movie) {
        Intent intent = new Intent(requireContext(), MovieDetailActivity.class);
        intent.putExtra(MovieDetailActivity.ARG_MOVIE, movie);
        startActivity(intent);
    }

    private void logMovieUrls(List<Movie> movieList) {
        for (Movie movie : movieList) {
            Log.d("URL", "Poster Path: " + movie.getPosterPath());
            Log.d("URL", "Backdrop Path: " + movie.getBackdropPath());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        logMovieUrls(movieList);
    }

    // Metode untuk memeriksa ketersediaan koneksi internet
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }
}
