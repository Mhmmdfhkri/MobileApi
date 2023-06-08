package com.example.final_mobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.final_mobile.R;

public class FavoritesFragment extends Fragment {

    private ImageView imgLike;
    private boolean isLiked = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);

        imgLike = view.findViewById(R.id.imgLike);
        imgLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLiked = !isLiked; // Toggle the like state

                if (isLiked) {
                    imgLike.setImageResource(R.mipmap.love); // Set the red heart image resource
                } else {
                    imgLike.setImageResource(R.mipmap.favorites); // Set the default heart image resource
                }
            }
        });

        return view;
    }
}
