package com.example.moviesapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.jsonResponse.DetailFilmResponse;
import com.example.moviesapp.model.Episode;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.onClickItem;

public class DetailFilmAdapter extends RecyclerView.Adapter<DetailFilmAdapter.DetailViewHolder> {
    private DetailFilmResponse detailFilmResponse;
    private onClickItem<Episode> onClickItem;

    public DetailFilmAdapter(DetailFilmResponse detailFilmResponse, com.example.moviesapp.onClickItem<Episode> onClickItem) {
        this.detailFilmResponse = detailFilmResponse;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public DetailFilmAdapter.DetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_detalfilm, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailFilmAdapter.DetailViewHolder holder, int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public static class DetailViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgBanner;
        private TextView movieName;
        public DetailViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBanner = itemView.findViewById(R.id.imageView22);
            movieName = itemView.findViewById(R.id.movieName);
        }
        public void bind(DetailFilmResponse detailFilmResponse){
            movieName.setText(detailFilmResponse.getMovie().getPosterUrl());
            Glide.with(itemView.getContext())
                    .load(detailFilmResponse.getMovie().getPosterUrl())
                    .into(imgBanner);
        }
    }
}
