package com.example.moviesapp.Adapters;

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
import com.example.moviesapp.Activities.MainActivity;
import com.example.moviesapp.R;
import com.example.moviesapp.ViewModel.DetailViewModel;
import com.example.moviesapp.ViewModel.HomeViewModel;
import com.example.moviesapp.model.ItemsMoviesNewUpdate;
import com.example.moviesapp.onClickItem;

import java.util.List;




public class MoviesNewUpdateAdapter extends RecyclerView.Adapter<MoviesNewUpdateAdapter.MovieViewHolder> {
private Context context;
    private onClickItem<ItemsMoviesNewUpdate> onClickItem;
    private List<ItemsMoviesNewUpdate> movies;

    public MoviesNewUpdateAdapter(Context context, com.example.moviesapp.onClickItem<ItemsMoviesNewUpdate> onClickItem, List<ItemsMoviesNewUpdate> movies) {
        this.context = context;
        this.onClickItem = onClickItem;
        this.movies = movies;
    }

    public MoviesNewUpdateAdapter(List<ItemsMoviesNewUpdate> movies) {
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_moviesnewupdate, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        ItemsMoviesNewUpdate movie = movies.get(position);
        holder.textViewMoviesTitle.setText(movie.getName());
        Glide.with(context)
                .load(movie.getPosterUrl())
                .into(holder.imageViewMoviesPoster);
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
    public static class MovieViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageViewMoviesPoster;
        private TextView textViewMoviesTitle;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewMoviesPoster = itemView.findViewById(R.id.imageView_movie_poster);
            textViewMoviesTitle = itemView.findViewById(R.id.textView_movie_title);

        }

    }
}
