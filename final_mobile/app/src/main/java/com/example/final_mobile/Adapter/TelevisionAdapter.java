package com.example.final_mobile.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_mobile.Model.Television;
import com.example.final_mobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TelevisionAdapter extends RecyclerView.Adapter<TelevisionAdapter.ViewHolder> {
    private List<Television> tvShows;
    private OnItemClickListener listener;

    public TelevisionAdapter(List<Television> tvShows, OnItemClickListener listener) {
        this.tvShows = tvShows;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_television, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Television tvShow = tvShows.get(position);
        holder.bind(tvShow, listener);
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public interface OnItemClickListener {
        void onItemClick(Television tvShow);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView releaseDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.ivPoster);
            titleTextView = itemView.findViewById(R.id.tvTitle);
            releaseDateTextView = itemView.findViewById(R.id.tvReleaseDate);
        }

        public void bind(final Television tvShow, final OnItemClickListener listener) {
            titleTextView.setText(tvShow.getTitle());
            releaseDateTextView.setText(tvShow.getReleaseDate());
            Picasso.get().load(tvShow.getPosterPath()).into(posterImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(tvShow);
                }
            });
        }
    }
}
