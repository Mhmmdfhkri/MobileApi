package com.example.final_mobile.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_mobile.Adapter.TelevisionAdapter;
import com.example.final_mobile.Class.Television;
import com.example.final_mobile.DetailActivity.TelevisionDetailActivity;
import com.example.final_mobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TelevisionFragment extends Fragment {
    private RecyclerView recyclerView;
    private TelevisionAdapter televisionAdapter;
    private List<Television> televisionList;

    private TextView internetConection;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_television, container, false);

        internetConection = view.findViewById(R.id.tv_internetConection);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        televisionList = new ArrayList<>();
        televisionAdapter = new TelevisionAdapter(televisionList, new TelevisionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Television television) {
                openTelevisionDetailActivity(television);
            }
        });
        recyclerView.setAdapter(televisionAdapter);

        fetchTelevision();

        return view;
    }

    // FetchTelevision method
    private void fetchTelevision() {
        // Periksa koneksi internet sebelum melakukan permintaan API
        if (!isNetworkAvailable()) {
            internetConection.setVisibility(View.VISIBLE);
            return;
        }

        String apiKey = "5fcb44f5991c765d4bfabcd479b852bf";
        String url = "https://api.themoviedb.org/3/tv/popular?api_key=" + apiKey;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject result = results.getJSONObject(i);
                                String title = result.getString("name");
                                String releaseDate = result.getString("first_air_date");
                                double voteAverage = result.getDouble("vote_average");
                                String overview = result.getString("overview");
                                String posterPath = result.getString("poster_path");
                                String backdropPath = result.getString("backdrop_path");

                                // Construct the full URL for the poster image
                                String posterUrl = "https://image.tmdb.org/t/p/w500" + posterPath;

                                Television television = new Television(title, releaseDate, voteAverage, overview, posterUrl, backdropPath);
                                televisionList.add(television);
                            }
                            televisionAdapter.notifyDataSetChanged();
                            logTelevisionUrls(televisionList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                        Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(request);
    }

    private void logTelevisionUrls(List<Television> televisionList) {
        for (Television television : televisionList) {
            Log.d("URL", "Poster Path: " + television.getPosterPath());
            Log.d("URL", "Backdrop Path: " + television.getBackdropPath());
        }
    }

    private void openTelevisionDetailActivity(Television television) {
        Intent intent = new Intent(getActivity(), TelevisionDetailActivity.class);
        intent.putExtra("television", television);
        startActivity(intent);
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
