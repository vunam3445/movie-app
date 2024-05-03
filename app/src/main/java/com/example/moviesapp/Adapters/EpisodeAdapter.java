package com.example.moviesapp.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.jsonResponse.DetailFilmResponse;
import com.example.moviesapp.model.Episode;
import com.example.moviesapp.model.ServerDatum;
import com.example.moviesapp.onClickItem;

import java.util.List;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.MyViewHolder> {
    private List<ServerDatum> episodes;
    private onClickItem<ServerDatum> onClickItem;

    public EpisodeAdapter(List<ServerDatum> episodes, onClickItem<ServerDatum> onClickItem) {
        this.episodes = episodes;
        this.onClickItem = onClickItem;
    }

    @NonNull
    @Override
    public EpisodeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeAdapter.MyViewHolder holder, int position) {
        if (episodes != null) {
            ServerDatum episode = episodes.get(position);
            holder.btnEpisode.setText(episode.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.onClick(episode);
                }
            });
        } else {
            Log.e("EpisodeAdapter", "Episodes list is null");
        }
    }

    @Override
    public int getItemCount() {
        return episodes != null ? episodes.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView btnEpisode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            btnEpisode = itemView.findViewById(R.id.btnEpisode);
        }
    }
}
