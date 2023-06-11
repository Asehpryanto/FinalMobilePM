package com.example.h071211065_finalmobile.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.h071211065_finalmobile.R;
import com.example.h071211065_finalmobile.activity.DetailActivity;
import com.example.h071211065_finalmobile.model.Favorite;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteViewHolder> {
    private List<Favorite> favorites;
    private ClickListener clickListener;

    public FavoriteAdapter(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public interface ClickListener {
        void onUserClicked(Favorite favoriteResponse);
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Favorite favorite = favorites.get(position);
        holder.setData(favorite, context);
    }

    @Override
    public int getItemCount() {
        return favorites != null ? favorites.size() : 0;
    }

    public class FavoriteViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImageView;
        private TextView titleTextView;
        private TextView yearTextView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            posterImageView = itemView.findViewById(R.id.ivPosterPath);
            titleTextView = itemView.findViewById(R.id.tvNamaFilm);
            yearTextView = itemView.findViewById(R.id.tvTahun);
        }

        public void setData(Favorite favorite, Context context) {
            String title = favorite.getTitle();
            String year = favorite.getReleaseDate();
            String poster = favorite.getPosterPath();
            titleTextView.setText(title);
            yearTextView.setText(year);
            Glide.with(context)
                    .load(poster)
                    .into(posterImageView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DetailActivity.class);
                    intent.putExtra("favorite", favorite);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
