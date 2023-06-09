package com.example.final_mobile.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.final_mobile.Adapter.TelevisionAdapter;
import com.example.final_mobile.Class.Television;
import com.example.final_mobile.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TelevisionFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView internetConnectionTextView;
    private TelevisionAdapter adapter;
    private List<Television> televisionList;

    public TelevisionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup fcontainer,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_television, fcontainer, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        internetConnectionTextView = view.findViewById(R.id.tv_internetConection);

        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setHasFixedSize(true);

        televisionList = new ArrayList<>();
        adapter = new TelevisionAdapter(televisionList, getActivity());
        recyclerView.setAdapter(adapter);

        // Check internet connection and load data
        if (isConnectedToInternet()) {
            loadTelevisionData();
        } else {
            showNoInternetConnectionMessage();
        }
        return view;
    }

    private boolean isConnectedToInternet() {
        // Implement your logic to check internet connectivity
        // Return true if connected, false otherwise
        return true; // For testing, assuming internet connection is available
    }

    private void loadTelevisionData() {
        String apiKey = "5fcb44f5991c765d4bfabcd479b852bf";
        String baseUrl = "https://api.themoviedb.org/3/tv/popular";
        String url = baseUrl + "?api_key=" + apiKey;

        // Create a new request using Volley
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Parse the JSON response
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject tvObject = response.getJSONObject(i);
                                String title = tvObject.getString("name");
                                String releaseDate = tvObject.getString("first_air_date");
                                String posterImageUrl = tvObject.getString("poster_path");
                                double voteAverage = tvObject.getDouble("vote_average");
                                String overview = tvObject.getString("overview");
                                String backdropPath = tvObject.getString("backdrop_path");

                                Television television = new Television(title, releaseDate, posterImageUrl);
                                television.setVoteAverage(voteAverage);
                                television.setOverview(overview);
                                television.setBackdropImageUrl(backdropPath);

                                televisionList.add(television);
                            }

                            // Notify the adapter that the data has changed
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

        // Add the request to the request queue
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

    private void showNoInternetConnectionMessage() {
        recyclerView.setVisibility(View.GONE);
        internetConnectionTextView.setVisibility(View.VISIBLE);
    }
}
