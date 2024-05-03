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
import com.example.moviesapp.model.Item;
import com.example.moviesapp.onClickItem;
import com.google.android.exoplayer2.util.Log;

import java.util.List;

public class IndividualFileAdapter extends RecyclerView.Adapter<IndividualFileAdapter.MyViewHolder> {
    private Context context;
    private List<Item> movies;
    private onClickItem<Item> onClickItem;

    public IndividualFileAdapter(Context context, List<Item> movies, com.example.moviesapp.onClickItem<Item> onClickItem) {
        this.context = context;
        this.movies = movies;
        this.onClickItem = onClickItem;
    }
    public void clear(){
        movies.clear();
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public IndividualFileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_individual,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IndividualFileAdapter.MyViewHolder holder, int position) {
            Item movie = movies.get(position);
            Log.e("namnamnam","https://img.phimapi.com/"+movie.getPosterUrl());

            holder.nameMovie.setText(movie.getName());
        if (context != null) {
            Glide.with(context)
                    .load("https://img.phimapi.com/"+movie.getPosterUrl())
                    .into(holder.img);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickItem.onClick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView more, nameMovie;
        private ImageView img;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            nameMovie = itemView.findViewById(R.id.textViewIndividual);
            img = itemView.findViewById(R.id.imageViewIndividual);
        }
    }
}
