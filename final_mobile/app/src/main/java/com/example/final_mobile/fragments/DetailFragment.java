package com.example.final_mobile.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.final_mobile.Class.Movie;
import com.example.final_mobile.R;

public class DetailFragment extends Fragment {

    private static final String ARG_MOVIE = "movie";

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView voteAverageTextView;
    private TextView overviewTextView;
    private ImageView backdropImageView;
    private ImageView btnLove;

    private Movie movie;

    public DetailFragment() {
        // Required empty public constructor
    }

    public static DetailFragment newInstance(Movie movie) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            movie = getArguments().getParcelable(ARG_MOVIE);
            setMovieImageUrls(movie); // Panggil metode untuk mengatur URL gambar pada objek Movie
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        posterImageView = view.findViewById(R.id.posterImageView);
        titleTextView = view.findViewById(R.id.titleTextView);
        releaseDateTextView = view.findViewById(R.id.releaseDateTextView);
        voteAverageTextView = view.findViewById(R.id.voteAverageTextView);
        overviewTextView = view.findViewById(R.id.overviewTextView);
        backdropImageView = view.findViewById(R.id.backdropImageView);
        btnLove = view.findViewById(R.id.btnLove);

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = v.isSelected();
                v.setSelected(!isSelected);
                if (isSelected) {
                    btnLove.setImageResource(R.mipmap.favo);
                } else {
                    btnLove.setImageResource(R.mipmap.fav);
                }
            }
        });

        Glide.with(requireContext())
                .load(movie.getPosterPath())
                .into(posterImageView);

        Log.d("URL", "Poster Path: " + movie.getPosterPath());

        titleTextView.setText(movie.getTitle());
        releaseDateTextView.setText(movie.getReleaseDate());
        voteAverageTextView.setText(String.valueOf(movie.getVoteAverage()));
        overviewTextView.setText(movie.getOverview());

        Glide.with(requireContext())
                .load(movie.getBackdropPath())
                .into(backdropImageView);

        Log.d("URL", "Backdrop Path: " + movie.getBackdropPath());

        return view;
    }

    private void setMovieImageUrls(Movie movie) {
        // Atur URL gambar menggunakan base URL dan nilai posterPath dan backdropPath
        String baseUrl = "https://image.tmdb.org/t/p/w500";
        String posterPath = movie.getPosterPath();
        String backdropPath = movie.getBackdropPath();

        movie.setPosterPath(baseUrl + posterPath);
        movie.setBackdropPath(baseUrl + backdropPath);
    }
}
