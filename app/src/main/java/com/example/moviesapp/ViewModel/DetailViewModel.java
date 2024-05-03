package com.example.moviesapp.ViewModel;

import static android.content.Intent.getIntent;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.jsonResponse.DetailFilmResponse;
import com.example.moviesapp.model.ItemsMoviesNewUpdate;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.retrofit.ApiServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;




public class DetailViewModel extends ViewModel {
    private MutableLiveData<DetailFilmResponse> Detail = new MutableLiveData<>();

    public LiveData<DetailFilmResponse> getDetail() {
        return Detail;
    }

    public void fetchMovieDetail(String movieName) {
        // Gọi API để lấy dữ liệu chi tiết của phim dựa trên tên phim
        ApiServer.ApiService.getDetailFilm(movieName)
                .enqueue(new Callback<DetailFilmResponse>() {
                    @Override
                    public void onResponse(Call<DetailFilmResponse> call, Response<DetailFilmResponse> response) {
                        if (response.isSuccessful()) {
                            DetailFilmResponse detailFilmResponse = response.body();
                            if (detailFilmResponse != null && detailFilmResponse.getStatus()) {
                                Detail.setValue(detailFilmResponse);
                                Log.e("datatata","haha");
                            }
                        } else {
                            Log.e("DetailViewModel", "Error response: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<DetailFilmResponse> call, Throwable t) {
                        Log.e("DetailViewModel", "Network failure: " + t.getMessage());
                    }
                });
    }
}
