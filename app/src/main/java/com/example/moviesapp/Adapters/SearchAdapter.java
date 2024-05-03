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

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    private Context context;
    private List<Item> movies;
    private onClickItem<Item> onClickItem;

    public SearchAdapter(Context context, List<Item> movies, com.example.moviesapp.onClickItem<Item> onClickItem) {
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
    public SearchAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.MyViewHolder holder, int position) {
        Item movie = movies.get(position);
        holder.nameMovie.setText(movie.getName());
        Glide.with(context)
                .load("https://img.phimapi.com/"+movie.getPosterUrl())
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
        return movies.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView nameMovie;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView7);
            nameMovie = itemView.findViewById(R.id.textView14);
        }
    }
}
