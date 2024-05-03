package com.example.moviesapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.example.moviesapp.Adapters.MoviesNewUpdateAdapter;
import com.example.moviesapp.Fragments.AnimesFragment;
import com.example.moviesapp.Fragments.IndividualFilmFragment;
import com.example.moviesapp.Fragments.SeriesFilmFragment;
import com.example.moviesapp.Fragments.TVShowsFragment;
import com.example.moviesapp.R;
import com.example.moviesapp.ViewModel.HomeViewModel;
import com.example.moviesapp.databinding.ActivityMainBinding;
import com.example.moviesapp.model.ItemsMoviesNewUpdate;
import com.example.moviesapp.onClickItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private ProgressBar progressMoviesNewUpdate;
    private MoviesNewUpdateAdapter moviesNewUpdateAdapter;
    private HomeViewModel homeViewModel;
    private ActivityMainBinding binding;

    // List để lưu trữ các Observer đã đăng ký
    private List<Observer<List<ItemsMoviesNewUpdate>>> observers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());
        event();
        Fragment fmIndividual = new IndividualFilmFragment();
        binding.textView19.setSelected(true);
        viewPager2 = findViewById(R.id.viewSlider);
        binding.textView15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListMoviesActivity.class);
                intent.putExtra("keyword","moviesnewupdate");

                startActivity(intent);
            }
        });

        loadFragment(fmIndividual);
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);




        homeViewModel.getMovies().observe(this, new Observer<List<ItemsMoviesNewUpdate>>() {
            @Override
            public void onChanged(List<ItemsMoviesNewUpdate> movies) {
                // Update UI with new list of movies
                moviesNewUpdateAdapter = new MoviesNewUpdateAdapter(MainActivity.this,new onClickItem<ItemsMoviesNewUpdate>() {
                    @Override
                    public void onClick(ItemsMoviesNewUpdate itemsMoviesNewUpdate) {
                        Intent intent = new Intent(MainActivity.this, DetalfilmActivity.class);
                        intent.putExtra("name",itemsMoviesNewUpdate.getSlug());
                        startActivity(intent);
                    }

                    @Override
                    public void delete(ItemsMoviesNewUpdate itemsMoviesNewUpdate) {

                    }
                }, movies);
                viewPager2.setAdapter(moviesNewUpdateAdapter);
                binding.progressMoviesNewUpdate.setVisibility(View.GONE);
            }
        });
        binding.progressMoviesNewUpdate.setVisibility(View.VISIBLE);
        // Fetch movies when activity starts
        homeViewModel.fetchMoviesNewUpdate(1);



    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Đóng activity
    }
    private void event() {
        binding.imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



        binding.editTextText2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Xử lý khi người dùng ấn Enter
                    String query = binding.editTextText2.getText().toString().trim();
                    if (!query.isEmpty()) {
                        // Gửi query đến hoạt động tìm kiếm
                        Intent intent = new Intent(MainActivity.this, ListMoviesActivity.class);
                        intent.putExtra("keywordsearch","search");
                        intent.putExtra("query", query);
                        startActivity(intent);
                        return true;
                    }
                }
                return false;

            }
        });

        binding.textView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fmIndividual = new IndividualFilmFragment();
                loadFragment(fmIndividual);
                binding.textView19.setSelected(true);
                binding.textView16.setSelected(false);
                binding.textView18.setSelected(false);
                binding.textView17.setSelected(false);

            }
        });
        binding.textView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fmSeries = new SeriesFilmFragment();
                loadFragment(fmSeries);
                binding.textView19.setSelected(false);
                binding.textView16.setSelected(false);
                binding.textView18.setSelected(true);
                binding.textView17.setSelected(false);
            }
        });
        binding.textView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fmAnime = new AnimesFragment();
                loadFragment(fmAnime);
                binding.textView19.setSelected(false);
                binding.textView16.setSelected(false);
                binding.textView18.setSelected(false);
                binding.textView17.setSelected(true);
            }
        });
        binding.textView16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fmTVShow = new TVShowsFragment();
                loadFragment(fmTVShow);
                binding.textView19.setSelected(false);
                binding.textView16.setSelected(true);
                binding.textView18.setSelected(false);
                binding.textView17.setSelected(false);
            }
        });

        binding.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FavouriteActivity.class);
                startActivity(intent);
            }
        });

    }

    void loadFragment(Fragment fmnew ){
        FragmentTransaction fmTran = getSupportFragmentManager().beginTransaction();
        fmTran.replace(R.id.main_frame,fmnew);
        fmTran.addToBackStack(null);
        fmTran.commit();
        binding.progressCategori.setVisibility(View.GONE);
    }



}


