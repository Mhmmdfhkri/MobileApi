package com.example.final_mobile.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.final_mobile.Class.Television;
import com.example.final_mobile.R;

public class TelevisionDetailFragment extends Fragment {
    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView voteAverageTextView;
    private TextView overviewTextView;
    private ImageView backdropImageView;

    private Television television;

    public static TelevisionDetailFragment newInstance(Television television) {
        TelevisionDetailFragment fragment = new TelevisionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("television", television);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_television_detail, container, false);

        posterImageView = view.findViewById(R.id.posterImageView);
        titleTextView = view.findViewById(R.id.titleTextView);
        releaseDateTextView = view.findViewById(R.id.releaseDateTextView);
        voteAverageTextView = view.findViewById(R.id.voteAverageTextView);
        overviewTextView = view.findViewById(R.id.overviewTextView);
        backdropImageView = view.findViewById(R.id.backdropImageView);

        Bundle args = getArguments();
        if (args != null && args.containsKey("television")) {
            television = args.getParcelable("television");
            if (television != null) {
                bindTelevision();
            }
        }
        return view;
    }

    private void bindTelevision() {
        Glide.with(requireContext())
                .load(television.getPosterPath())
                .into(posterImageView);

        Log.d("URL", "Poster Path: " + television.getPosterPath());

        titleTextView.setText(television.getTitle());
        releaseDateTextView.setText(television.getReleaseDate());
        voteAverageTextView.setText(String.valueOf(television.getVoteAverage()));
        overviewTextView.setText(television.getOverview());

        // Memeriksa dan memuat gambar backdrop jika URL-nya valid
        if (isValidUrl(television.getBackdropPath())) {
            Glide.with(requireContext())
                    .load(television.getBackdropPath())
                    .into(backdropImageView);

            Log.d("URL", "Backdrop Path: " + television.getBackdropPath());
        } else {
            // Jika URL backdrop tidak valid, dapatkan URL placeholder atau lakukan tindakan lain
            // Misalnya, Anda dapat menggunakan Glide untuk memuat gambar placeholder:
            Glide.with(requireContext())
                    .load(R.drawable.ic_launcher_background) // Ganti dengan sumber daya gambar placeholder yang sesuai
                    .into(backdropImageView);

            Log.d("URL", "Invalid Backdrop URL");
        }
    }

    private boolean isValidUrl(String url) {
        // Implementasi sederhana untuk memeriksa validitas URL
        // Anda dapat mengganti logika ini dengan cara yang lebih robust sesuai kebutuhan aplikasi Anda
        return url != null && !url.isEmpty() && (url.startsWith("http://") || url.startsWith("https://"));
    }
}
