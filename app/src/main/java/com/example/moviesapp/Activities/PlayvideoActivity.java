package com.example.moviesapp.Activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Adapters.EpisodeAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.ViewModel.DetailViewModel;
import com.example.moviesapp.databinding.ActivityPlayvideoBinding;
import com.example.moviesapp.jsonResponse.DetailFilmResponse;
import com.example.moviesapp.model.ServerDatum;
import com.example.moviesapp.onClickItem;

public class PlayvideoActivity extends AppCompatActivity {
    private TextView nameFilm, episode;
    private ImageView back;
    private EpisodeAdapter episodeAdapter;
    private DetailViewModel detailViewModel;
    private RecyclerView recyclerPlay;
    private String name, url, epi, nameMovie;
    private ImageView imgBanner, imgBack;
    private VideoView video;
    private Button fullscreenButton;
    public MediaController mediaController;
    public ActivityPlayvideoBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playvideo);
        binding = ActivityPlayvideoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayvideoActivity.this,MainActivity.class);
                intent.putExtra("home",true);

                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        Bundle intent = getIntent().getExtras();
        name = intent.getString("nameFilm");
        epi = intent.getString("episode");
        url = intent.getString("url");
        nameMovie = intent.getString("nameMovie");
        event();

        video.setVideoURI(Uri.parse(url));
        mediaController.setAnchorView(video);
        mediaController.setMediaPlayer(video);
        video.setMediaController(mediaController);
        video.start();

        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        detailViewModel.fetchMovieDetail(nameMovie);

        detailViewModel.getDetail().observe(this, new Observer<DetailFilmResponse>() {
            @Override
            public void onChanged(DetailFilmResponse detailFilmResponse) {
                episodeAdapter = new EpisodeAdapter(detailFilmResponse.getEpisodes().get(0).getServerData(), new onClickItem<ServerDatum>() {
                    @Override
                    public void onClick(ServerDatum serverDatum) {
                        name = serverDatum.getFilename();
                        epi = serverDatum.getName();
                        url = serverDatum.getLinkM3u8();
                        nameFilm.setText(name);
                        episode.setText(epi);
                        video.setVideoURI(Uri.parse(url));
                        video.start();
                    }

                    @Override
                    public void delete(ServerDatum serverDatum) {

                    }
                });

                GridLayoutManager gridLayoutManager = new GridLayoutManager(PlayvideoActivity.this, 3);
                recyclerPlay.setLayoutManager(gridLayoutManager);
                recyclerPlay.setAdapter(episodeAdapter);
            }
        });

        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFullscreen();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (getIntent().getBooleanExtra("home", false)) {
            // Nếu activity này được khởi động từ activity thứ hai
            // Kết thúc activity hiện tại và quay lại trang chủ
            finish();
        } else {
            super.onBackPressed();
        }
    }
    public void toggleFullscreen() {
        Intent full = new Intent(PlayvideoActivity.this, FullscreenActivity.class);
        full.putExtra("url",url);
        startActivity(full);


    }



    public void init() {
        nameFilm = findViewById(R.id.textView11);
        episode = findViewById(R.id.textView13);
        back = findViewById(R.id.imageView5);
        video = findViewById(R.id.videoView);
        recyclerPlay = findViewById(R.id.recyclerPlay);
        fullscreenButton = findViewById(R.id.fullscreenButton);
        mediaController = new MediaController(this);
    }

    public void event() {
        nameFilm.setText(name);
        episode.setText(epi);
        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlayvideoActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
