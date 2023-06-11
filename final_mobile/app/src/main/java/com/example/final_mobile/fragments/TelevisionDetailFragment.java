package com.example.final_mobile.fragments;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.final_mobile.Database.DatabaseHelperTelevision;

import androidx.fragment.app.Fragment;

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
    private DatabaseHelperTelevision databaseHelper;

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
        databaseHelper = new DatabaseHelperTelevision(requireContext());
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

        // Cek apakah serial ini merupakan favorit
        final boolean[] isFavorite = {databaseHelper.isFavorite(tvShow.getTitle())};
        setFavoriteIcon(isFavorite[0]);

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isSelected = v.isSelected();
                v.setSelected(!isSelected);
                isFavorite[0] = !isSelected;
                setFavoriteIcon(isFavorite[0]);

                if (isFavorite[0]) {
                    // Tambahkan ke favorit dan simpan ke database
                    databaseHelper.addFavorite(tvShow.getTitle());
                    showToast("Added to favorites");
                } else {
                    // Hapus dari favorit dalam database
                    databaseHelper.removeFavorite(tvShow.getTitle());
                    showToast("Removed from favorites");
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

    private void setFavoriteIcon(boolean isFavorite) {
        if (isFavorite) {
            btnLove.setImageResource(R.mipmap.fav);
        } else {
            btnLove.setImageResource(R.mipmap.favo);
        }
    }

    private void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
