package com.example.final_mobile.Adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.final_mobile.Class.Television;
import com.example.final_mobile.R;

import java.util.List;

public class TelevisionAdapter extends RecyclerView.Adapter<TelevisionAdapter.ViewHolder> {
    private List<Television> televisionList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Television television);
    }

    public TelevisionAdapter(List<Television> televisionList, OnItemClickListener onItemClickListener) {
        this.televisionList = televisionList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Television television = televisionList.get(position);
        holder.bind(television);
    }

    @Override
    public int getItemCount() {
        return televisionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView posterImageView;
        private ImageView backdropImageView;
        private TextView titleTextView;
        private TextView yearTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            backdropImageView = itemView.findViewById(R.id.backdropImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);

            itemView.setOnClickListener(this);
        }

        public void bind(Television television) {
            // Set the data to the views
            titleTextView.setText(television.getTitle());
            yearTextView.setText(television.getReleaseDate());

            // Use Glide library to load the poster image
            Glide.with(itemView.getContext())
                    .load(television.getPosterPath())
                    .into(posterImageView);

            // Use Glide library to load the backdrop image as the background
            Glide.with(itemView.getContext())
                    .load(television.getBackdropPath())
                    .into(new SimpleTarget<Drawable>() {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            backdropImageView.setImageDrawable(resource);
                        }
                    });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Television television = televisionList.get(position);
                onItemClickListener.onItemClick(television);
            }
        }
    }
}
