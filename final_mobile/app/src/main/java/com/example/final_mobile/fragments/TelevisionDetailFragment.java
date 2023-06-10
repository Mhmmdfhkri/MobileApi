package com.example.final_mobile.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.final_mobile.Class.Television;
import com.example.final_mobile.R;

public class TelevisionDetailFragment extends Fragment {
    private static final String ARG_TV_SHOW = "tvShow";

    private Television tvShow;
    private ImageView posterImageView;
    private ImageView backdropImageView;
    private TextView titleTextView;
    private TextView ratingTextView;
    private TextView yearTextView;
    private TextView synopsisTextView;
    private ImageView btnLove;

    public static TelevisionDetailFragment newInstance(Television tvShow) {
        TelevisionDetailFragment fragment = new TelevisionDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_TV_SHOW, tvShow);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tvShow = getArguments().getParcelable(ARG_TV_SHOW);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_television_detail, container, false);
        posterImageView = view.findViewById(R.id.ivPoster);
        backdropImageView = view.findViewById(R.id.ivBackdrop);
        titleTextView = view.findViewById(R.id.tvTitle);
        ratingTextView = view.findViewById(R.id.tvVoteAverage);
        yearTextView = view.findViewById(R.id.tvReleaseDate);
        synopsisTextView = view.findViewById(R.id.tvOverview);
        btnLove = view.findViewById(R.id.btn_love);

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

        // Set data to views
        Glide.with(requireContext()).load(tvShow.getPosterPath()).into(posterImageView);

        // Check if backdrop path is available
        if (tvShow.getBackdropPath() != null && !tvShow.getBackdropPath().isEmpty()) {
            Glide.with(requireContext()).load(tvShow.getBackdropPath()).into(backdropImageView);
            backdropImageView.setVisibility(View.VISIBLE);
        } else {
            Glide.with(requireContext()).load(R.drawable.ic_launcher_background).into(backdropImageView);
            backdropImageView.setVisibility(View.VISIBLE);
        }

        titleTextView.setText(tvShow.getTitle());
        ratingTextView.setText(String.valueOf(tvShow.getVoteAverage()));
        yearTextView.setText(tvShow.getReleaseDate());
        synopsisTextView.setText(tvShow.getOverview());

        return view;
    }
}
