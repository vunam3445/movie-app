package com.example.moviesapp.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.moviesapp.Adapters.FavouriteAdapter;
import com.example.moviesapp.R;
import com.example.moviesapp.Roomdatabase.FavouriteDatabase;
import com.example.moviesapp.ViewModel.FavouriteViewModel;
import com.example.moviesapp.databinding.ActivityDetalfilmBinding;
import com.example.moviesapp.databinding.ActivityFavouriteBinding;
import com.example.moviesapp.model.movieFavourite;
import com.example.moviesapp.onClickItem;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    private FavouriteAdapter favouriteAdapter;
    private ActivityFavouriteBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityFavouriteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


//        FavouriteViewModel favouriteViewModel = new ViewModelProvider(this).get(FavouriteViewModel.class);
//        favouriteViewModel.getFavourite().observe(this, new Observer<List<movieFavourite>>() {
//            @Override
//            public void onChanged(List<movieFavourite> movieFavourites) {
//
//            }
//        });
//
        loadData();





        binding.home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(FavouriteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        List<movieFavourite> movies = new ArrayList<>();
        movies.addAll(FavouriteDatabase.getInstance(FavouriteActivity.this).favouriteDao().getFavourite());
        favouriteAdapter = new FavouriteAdapter(FavouriteActivity.this,movies, new onClickItem<movieFavourite>() {
            @Override
            public void onClick(movieFavourite movieFavourite) {
                Intent intent = new Intent(FavouriteActivity.this,DetalfilmActivity.class);
                intent.putExtra("name",movieFavourite.getSlugname());
                startActivity(intent);
            }

            @Override
            public void delete(movieFavourite movieFavourite) {
                new AlertDialog.Builder(FavouriteActivity.this)
                        .setTitle("Confirm delete favourite")
                        .setMessage("Are you sure?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FavouriteDatabase.getInstance(FavouriteActivity.this).favouriteDao().deleteFavourite(movieFavourite);

                                loadData();
                                Toast.makeText(FavouriteActivity.this,"delete successfully",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No",null)
                        .show();
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        binding.recyclerFavourite.setLayoutManager(gridLayoutManager);
        binding.recyclerFavourite.setAdapter(favouriteAdapter);
    }
}
