package com.example.moviesapp.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.Roomdatabase.FavouriteDatabase;
import com.example.moviesapp.model.movieFavourite;

import java.util.List;

public class FavouriteViewModel extends ViewModel {
    private MutableLiveData<List<movieFavourite>> movies = new MutableLiveData<>();
    private Context context;

    public FavouriteViewModel(Context context) {
        this.context = context;
        fetchFavourite();
    }

    public LiveData<List<movieFavourite>> getFavourite() {
        return movies;
    }

    public void fetchFavourite() {
        if (context != null) {
            movies.setValue(FavouriteDatabase.getInstance(context.getApplicationContext()).favouriteDao().getFavourite());
        }
    }
}
