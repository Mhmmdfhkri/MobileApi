package com.example.final_mobile.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.final_mobile.Model.FavoriteItem;
import com.example.final_mobile.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {

    private Context context;
    private List<FavoriteItem> favoriteList;

    public FavoriteAdapter(Context context, List<FavoriteItem> favoriteList) {
        this.context = context;
        this.favoriteList = favoriteList;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteItem favoriteItem = favoriteList.get(position);

        holder.titleTextView.setText(favoriteItem.getTitle());
        holder.voteAverageTextView.setText(String.valueOf(favoriteItem.getVoteAverage()));

        Glide.with(context).load(favoriteItem.getPosterPath()).into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView;
        public TextView voteAverageTextView;
        public ImageView posterImageView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.tvTitle);
            voteAverageTextView = itemView.findViewById(R.id.tvVoteAverage);
            posterImageView = itemView.findViewById(R.id.ivPoster);
        }
    }
}
