package com.example.final_mobile.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.final_mobile.Class.Television;
import com.example.final_mobile.DetailActivity.TelevisionDetailActivity;
import com.example.final_mobile.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TelevisionAdapter extends RecyclerView.Adapter<TelevisionAdapter.ViewHolder> {
    private List<Television> televisionList;
    private Context context;

    public TelevisionAdapter(List<Television> televisionList, Context context) {
        this.televisionList = televisionList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView releaseDateTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            releaseDateTextView = itemView.findViewById(R.id.yearTextView);
        }
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

        holder.titleTextView.setText(television.getTitle());
        holder.releaseDateTextView.setText(television.getReleaseDate());

        // Load the poster image using Picasso
        Picasso.get().load(television.getPosterImageUrl()).placeholder(R.drawable.ic_launcher_background).into(holder.posterImageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click event, e.g., open the detail activity
                Intent intent = new Intent(context, TelevisionDetailActivity.class);
                intent.putExtra(TelevisionDetailActivity.ARG_TELEVISION, television);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return televisionList.size();
    }
}
