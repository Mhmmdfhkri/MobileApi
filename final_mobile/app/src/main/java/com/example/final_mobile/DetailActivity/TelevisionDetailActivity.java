package com.example.final_mobile.DetailActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.final_mobile.Class.Television;
import com.example.final_mobile.R;
import com.squareup.picasso.Picasso;

public class TelevisionDetailActivity extends AppCompatActivity {
    public static final String ARG_TELEVISION = "television";
    private ImageView backdropImageView;
    private TextView titleTextView;
    private TextView releaseDateTextView;
    private TextView voteAverageTextView;
    private TextView overviewTextView;
    private ImageView posterImageView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_television_detail);

        backdropImageView = findViewById(R.id.backdropImageView);
        titleTextView = findViewById(R.id.titleTextView);
        releaseDateTextView = findViewById(R.id.releaseDateTextView);
        voteAverageTextView = findViewById(R.id.voteAverageTextView);
        overviewTextView = findViewById(R.id.overviewTextView);
        posterImageView = findViewById(R.id.posterImageView);

        // Get the television object from the intent
        Television television = getIntent().getParcelableExtra("television");

        if (television != null) {
            titleTextView.setText(television.getTitle());
            releaseDateTextView.setText(television.getReleaseDate());
            voteAverageTextView.setText(String.valueOf(television.getVoteAverage()));
            overviewTextView.setText(television.getOverview());

            // Load the poster and backdrop images using a library like Picasso or Glide
            // For example:
            Picasso.get().load(television.getPosterImageUrl()).placeholder(R.drawable.ic_launcher_background).into(posterImageView);
            Picasso.get().load(television.getBackdropImageUrl()).placeholder(R.drawable.ic_launcher_background).into(backdropImageView);
        }
    }
}
