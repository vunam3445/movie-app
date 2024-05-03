package com.example.moviesapp.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.moviesapp.model.movieFavourite;

import java.util.List;

@Dao
public interface FavouriteDao {
    @Query("select * from Favourites")
    List<movieFavourite> getFavourite();
    @Insert
    void insertFavourite(movieFavourite movie);
    @Query("select * from Favourites where slugname=:slugname")
    List<movieFavourite> check(String slugname);
    @Delete
    void deleteFavourite(movieFavourite movie);
}
