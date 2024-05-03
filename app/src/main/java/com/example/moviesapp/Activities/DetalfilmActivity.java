package com.example.moviesapp.Activities;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.Adapters.DetailFilmAdapter;
import com.example.moviesapp.Adapters.EpisodeAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.Roomdatabase.FavouriteDatabase;
import com.example.moviesapp.ViewModel.DetailViewModel;
import com.example.moviesapp.databinding.ActivityDetalfilmBinding;
import com.example.moviesapp.databinding.ActivityMainBinding;
import com.example.moviesapp.jsonResponse.DetailFilmResponse;
import com.example.moviesapp.model.Episode;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.model.ServerDatum;
import com.example.moviesapp.model.movieFavourite;
import com.example.moviesapp.onClickItem;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.List;


public class DetalfilmActivity extends AppCompatActivity {
    private DetailViewModel detailViewModel;

    private DetailFilmAdapter detailFilmAdapter;
    private EpisodeAdapter episodeAdapter;
    private ImageView imgBanner,imgBack;
    private TextView movieName,content;
    private RecyclerView recyclerViewEpisode;
    private ActivityDetalfilmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalfilm);
        binding = ActivityDetalfilmBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        final String slutName,scrip,img;

        imgBanner = findViewById(R.id.imageView22);
        movieName = findViewById(R.id.movieName);
        imgBack = findViewById(R.id.imageView18);

        recyclerViewEpisode = findViewById(R.id.recyclerViewEpisode);

        Bundle intent = getIntent().getExtras();
        String name = intent.getString("name");




        // Khởi tạo ViewModel
        detailViewModel = new ViewModelProvider(this).get(DetailViewModel.class);

        // Lấy tên phim từ Intent

        // Gọi phương thức để lấy dữ liệu chi tiết của phim dựa trên tên phim
        detailViewModel.fetchMovieDetail(name);

        // Quan sát thay đổi của dữ liệu chi tiết phim
        detailViewModel.getDetail().observe(this, new Observer<DetailFilmResponse>() {
            @Override
            public void onChanged(DetailFilmResponse detailFilmResponse) {
                Glide.with(DetalfilmActivity.this).load(detailFilmResponse.getMovie().getPosterUrl()).into(imgBanner);
                movieName.setText(detailFilmResponse.getMovie().getName());
                binding.content.setText(detailFilmResponse.getMovie().getContent());

                final String name1= detailFilmResponse.getMovie().getName();

                 final String slugName= detailFilmResponse.getMovie().getSlug();
                 final  String scrip = detailFilmResponse.getMovie().getContent();
                 final String img = detailFilmResponse.getMovie().getPosterUrl();

                binding.imageView19.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isFavourite(slugName)){
                            Toast.makeText(DetalfilmActivity.this,"Đã tồn tại",Toast.LENGTH_SHORT).show();
                        }else {

                            movieFavourite movie = new movieFavourite(name1,slugName,scrip,img);
                            FavouriteDatabase.getInstance(DetalfilmActivity.this).favouriteDao().insertFavourite(movie);
                            Toast.makeText(DetalfilmActivity.this,"Them thanh cong ",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                episodeAdapter = new EpisodeAdapter(detailFilmResponse.getEpisodes().get(0).getServerData(), new onClickItem<ServerDatum>() {
                    @Override
                    public void onClick(ServerDatum serverDatum) {
                        Intent intentPlay = new Intent(DetalfilmActivity.this,PlayvideoActivity.class);
                        intentPlay.putExtra("nameFilm",serverDatum.getFilename());
                        intentPlay.putExtra("episode",serverDatum.getName());
                        intentPlay.putExtra("url",serverDatum.getLinkM3u8());

                        intentPlay.putExtra("nameMovie",detailFilmResponse.getMovie().getSlug());


                        startActivity(intentPlay);
                    }

                    @Override
                    public void delete(ServerDatum serverDatum) {

                    }
                });
                binding.progressDetal.setVisibility(View.GONE);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(DetalfilmActivity.this, 3);
                recyclerViewEpisode.setLayoutManager(gridLayoutManager);
                recyclerViewEpisode.setAdapter(episodeAdapter);



            }
        });



        event();
    }

    private void event() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(DetalfilmActivity.this,MainActivity.class);
//                startActivity(intent);
                finish();

            }
        });

        binding.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalfilmActivity.this,FavouriteActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean isFavourite(String slugname){
        List<movieFavourite> list = FavouriteDatabase.getInstance(DetalfilmActivity.this).favouriteDao().check(slugname);
        return list !=null && !list.isEmpty();
    }

    // Phương thức này sẽ cập nhật giao diện với dữ liệu chi tiết của phim

}
