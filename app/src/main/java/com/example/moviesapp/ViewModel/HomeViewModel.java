package com.example.moviesapp.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.jsonResponse.IndividualFilmResponse;
import com.example.moviesapp.jsonResponse.MoviesNewUpdateResponse;
import com.example.moviesapp.model.Item;
import com.example.moviesapp.model.ItemsMoviesNewUpdate;
import com.example.moviesapp.model.Movie;
import com.example.moviesapp.retrofit.ApiServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<ItemsMoviesNewUpdate>> moviesnewupdate = new MutableLiveData<>();
    private MutableLiveData<List<Item>> moviesIndividual = new MutableLiveData<>();
    private MutableLiveData<List<Item>> moviesSeries = new MutableLiveData<>();
    private MutableLiveData<List<Item>> moviesAnimes = new MutableLiveData<>();
    private MutableLiveData<List<Item>> moviesTVShows = new MutableLiveData<>();


    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public LiveData<List<Item>> getIndividual(){return moviesIndividual;}
    public LiveData<List<Item>> getTVShows(){
        return moviesTVShows;
    }
    public LiveData<List<Item>> getAnimes(){
        return moviesAnimes;
    }
    public LiveData<List<Item>> getSeries(){
        return moviesSeries;
    }

    public LiveData<List<ItemsMoviesNewUpdate>> getMovies() {
        return moviesnewupdate;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    private int page =1;
    public void fetchMoviesNewUpdate( int page) {
        isLoading.setValue(true);
        ApiServer.ApiService.getNewestMovies(page)
                .enqueue(new Callback<MoviesNewUpdateResponse>() {
                    @Override
                    public void onResponse(Call<MoviesNewUpdateResponse> call, Response<MoviesNewUpdateResponse> response) {
                        isLoading.setValue(false);
                        if (response.isSuccessful()) {
                            MoviesNewUpdateResponse moviesNewUpdateResponse = response.body();
                            if (moviesNewUpdateResponse != null && moviesNewUpdateResponse.getStatus()) {
                                moviesnewupdate.setValue(moviesNewUpdateResponse.getItems());
                                Log.e("namnam","hahahah");
                            }
                        } else {
                            Log.e("MoviesViewModel", "Error response: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<MoviesNewUpdateResponse> call, Throwable t) {
                        isLoading.setValue(false);
                        Log.e("MoviesViewModel", "Network failure: " + t.getMessage());
                    }
                });
    }

    public void fetchIndividual(int page){
        ApiServer.ApiService.getIndividualFilm(page)
                .enqueue(new Callback<IndividualFilmResponse>() {
                    @Override
                    public void onResponse(Call<IndividualFilmResponse> call, Response<IndividualFilmResponse> response) {
                        if(response.isSuccessful()){
                            IndividualFilmResponse individualFilmResponse = response.body();
                            if(individualFilmResponse != null && individualFilmResponse.getStatus().equals("success")){
                                moviesIndividual.setValue(individualFilmResponse.getData().getItems());
                                Log.e("IndividualFileViewModel","success");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<IndividualFilmResponse> call, Throwable t) {
                        Log.e("IndividualFileViewModel",t.getMessage());
                    }
                });
    }

    public void fetchSeries(int page){
        ApiServer.ApiService.getSeriesFilm(page)
                .enqueue(new Callback<IndividualFilmResponse>() {
                    @Override
                    public void onResponse(Call<IndividualFilmResponse> call, Response<IndividualFilmResponse> response) {
                        if(response.isSuccessful()){
                            IndividualFilmResponse individualFilmResponse = response.body();
                            if(individualFilmResponse != null && individualFilmResponse.getStatus().equals("success")){
                                moviesSeries.setValue(individualFilmResponse.getData().getItems());
                                Log.e("IndividualFileViewModel","success");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<IndividualFilmResponse> call, Throwable t) {
                        Log.e("IndividualFileViewModel",t.getMessage());
                    }
                });
    }

    public void fetchAnimes(int page){
        ApiServer.ApiService.getAnimesFilm(page)
                .enqueue(new Callback<IndividualFilmResponse>() {
                    @Override
                    public void onResponse(Call<IndividualFilmResponse> call, Response<IndividualFilmResponse> response) {
                        if(response.isSuccessful()){
                            IndividualFilmResponse individualFilmResponse = response.body();
                            if(individualFilmResponse != null && individualFilmResponse.getStatus().equals("success")){
                                moviesAnimes.setValue(individualFilmResponse.getData().getItems());
                                Log.e("IndividualFileViewModel","success");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<IndividualFilmResponse> call, Throwable t) {
                        Log.e("IndividualFileViewModel",t.getMessage());
                    }
                });
    }

    public void fetchTVShows(int page){
        ApiServer.ApiService.getTVShowsFilm(page)
                .enqueue(new Callback<IndividualFilmResponse>() {
                    @Override
                    public void onResponse(Call<IndividualFilmResponse> call, Response<IndividualFilmResponse> response) {
                        if(response.isSuccessful()){
                            IndividualFilmResponse individualFilmResponse = response.body();
                            if(individualFilmResponse != null && individualFilmResponse.getStatus().equals("success")){
                                moviesTVShows.setValue(individualFilmResponse.getData().getItems());
                                Log.e("IndividualFileViewModel","success");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<IndividualFilmResponse> call, Throwable t) {
                        Log.e("IndividualFileViewModel",t.getMessage());
                    }
                });
    }
}

