package com.example.moviesapp.Adapters;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Activities.DetalfilmActivity;
import com.example.moviesapp.R;
import com.example.moviesapp.Roomdatabase.FavouriteDatabase;
import com.example.moviesapp.model.movieFavourite;
import com.example.moviesapp.onClickItem;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    Context context;
    private List<movieFavourite> favourites;
    private onClickItem<movieFavourite> onClickItem;

    public FavouriteAdapter(Context context, List<movieFavourite> favourites, com.example.moviesapp.onClickItem<movieFavourite> onClickItem) {
        this.context = context;
        this.favourites = favourites;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite,parent,false);
       return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        movieFavourite movie = favourites.get(position);

        holder.name.setText(movie.getName());
        holder.scrip.setText(movie.getScrip());
        Glide.with(context).load(movie.getImg()).into(holder.img);
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.delete(movie);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favourites.size();
    }

    public class FavouriteViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView name,scrip,delete;
        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView8);
            name = itemView.findViewById(R.id.textView10);
            scrip = itemView.findViewById(R.id.scrip);
            delete = itemView.findViewById(R.id.textView22);
        }
    }
}
