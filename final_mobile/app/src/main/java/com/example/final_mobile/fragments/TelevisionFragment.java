package com.example.final_mobile.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_mobile.Adapter.TelevisionAdapter;
import com.example.final_mobile.Model.Television;
import com.example.final_mobile.DetailActivity.TelevisionDetailActivity;
import com.example.final_mobile.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class TelevisionFragment extends Fragment implements TelevisionAdapter.OnItemClickListener {
    private RecyclerView recyclerView;
    private TelevisionAdapter adapter;
    private List<Television> tvShows = new ArrayList<>();
    private TextView internetConnection;

    private static final String API_KEY = "5fcb44f5991c765d4bfabcd479b852bf";
    private static final String API_URL = "https://api.themoviedb.org/3/tv/popular?api_key=" + API_KEY;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_television, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        internetConnection = view.findViewById(R.id.tv_internet);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
        adapter = new TelevisionAdapter(tvShows, this);
        recyclerView.setAdapter(adapter);

        if (isNetworkAvailable()) {
            fetchTVShows();
            internetConnection.setVisibility(View.VISIBLE);
        } else {
            internetConnection.setVisibility(View.VISIBLE);
        }
        return view;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
        return false;
    }

    private void fetchTVShows() {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, API_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray results = response.getJSONArray("results");
                            for (int i = 0; i < results.length(); i++) {
                                JSONObject tvShowObject = results.getJSONObject(i);
                                String title = tvShowObject.getString("name");
                                String releaseDate = tvShowObject.getString("first_air_date");
                                String posterPath = "https://image.tmdb.org/t/p/w500" +
                                        tvShowObject.getString("poster_path");
                                String overview = tvShowObject.getString("overview");
                                String backdropPath = "https://image.tmdb.org/t/p/w500" +
                                        tvShowObject.getString("backdrop_path");
                                double voteAverage = tvShowObject.getDouble("vote_average");

                                Television tvShow = new Television(title, releaseDate, posterPath, overview, backdropPath, voteAverage);
                                tvShows.add(tvShow);
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        Volley.newRequestQueue(requireContext()).add(request);
    }

    @Override
    public void onItemClick(Television tvShow) {
        Intent intent = new Intent(requireContext(), TelevisionDetailActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }
}
