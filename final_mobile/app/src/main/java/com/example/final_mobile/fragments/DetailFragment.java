package com.example.final_mobile.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.final_mobile.Class.Movie;
import com.example.final_mobile.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {

    private static final String ARG_MOVIE = "movie";

    private ImageView posterImageView;
    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView voteAverageTextView;
    private TextView overviewTextView;
    private ImageView backdropImageView;

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        posterImageView = view.findViewById(R.id.posterImageView);
        titleTextView = view.findViewById(R.id.titleTextView);
        releaseDateTextView = view.findViewById(R.id.releaseDateTextView);
        voteAverageTextView = view.findViewById(R.id.voteAverageTextView);
        overviewTextView = view.findViewById(R.id.overviewTextView);
        backdropImageView = view.findViewById(R.id.backdropImageView);

        bindMovie();

        return view;
    }

    private void bindMovie() {
        // Use Glide library to load images
        Glide.with(requireContext())
                .load(movie.getPosterPath())
                .into(posterImageView);

        titleTextView.setText(movie.getTitle());
        releaseDateTextView.setText(movie.getReleaseDate());
        voteAverageTextView.setText(String.valueOf(movie.getVoteAverage()));
        overviewTextView.setText(movie.getOverview());

        Glide.with(requireContext())
                .load(movie.getBackdropPath())
                .into(backdropImageView);
    }
}
