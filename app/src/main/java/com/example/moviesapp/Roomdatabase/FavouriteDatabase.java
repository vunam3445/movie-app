package com.example.moviesapp.Roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.moviesapp.Dao.FavouriteDao;
import com.example.moviesapp.model.movieFavourite;

@Database(entities = {movieFavourite.class}, version = 1)
public abstract class FavouriteDatabase extends RoomDatabase {
    private static final String DATABASE_NAME="favourites.db";
    private static FavouriteDatabase instance;
    public static synchronized FavouriteDatabase getInstance(Context context){
        if( instance ==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),FavouriteDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    public abstract FavouriteDao favouriteDao();
}
