package com.example.moviesapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.model.ItemsMoviesNewUpdate;
import com.example.moviesapp.onClickItem;

import java.util.List;

public class MoreFilmAdapter extends RecyclerView.Adapter<MoreFilmAdapter.MoreFilmViewHolder> {
    private Context context;
    private onClickItem<ItemsMoviesNewUpdate> onClickItem;
    private List<ItemsMoviesNewUpdate> movies;

    public MoreFilmAdapter(Context context, com.example.moviesapp.onClickItem<ItemsMoviesNewUpdate> onClickItem, List<ItemsMoviesNewUpdate> movies) {
        this.context = context;
        this.onClickItem = onClickItem;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MoreFilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
       return new MoreFilmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoreFilmViewHolder holder, int position) {
        ItemsMoviesNewUpdate movie = movies.get(position);
        holder.nameMovie.setText(movie.getName());
        Glide.with(context)
                .load(movie.getPosterUrl())
                .into(holder.img);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size() ;
    }
    public static class MoreFilmViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView nameMovie;
        public MoreFilmViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView7);
            nameMovie = itemView.findViewById(R.id.textView14);

        }
    }
}
