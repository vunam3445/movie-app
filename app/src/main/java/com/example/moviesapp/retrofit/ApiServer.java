package com.example.moviesapp.retrofit;

import com.example.moviesapp.jsonResponse.DetailFilmResponse;
import com.example.moviesapp.jsonResponse.IndividualFilmResponse;
import com.example.moviesapp.jsonResponse.MoviesNewUpdateResponse;
import com.example.moviesapp.jsonResponse.MoviesNewUpdateResponse;
import com.example.moviesapp.jsonResponse.SearchResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiServer {
    HttpLoggingInterceptor HTTP_LOGGING_INTERCEPTOR = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(HTTP_LOGGING_INTERCEPTOR).build();

    Gson gson = new GsonBuilder().setDateFormat("dd-MM-yyyy").create();

    ApiServer ApiService = new Retrofit.Builder()
            .baseUrl("https://phimapi.com/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))

            .build()
            .create(ApiServer.class);

    @GET("danh-sach/phim-moi-cap-nhat")
    Call<MoviesNewUpdateResponse> getNewestMovies(@Query("page") int page);
    @GET("phim/{name}")
    Call<DetailFilmResponse> getDetailFilm(@Path("name") String name);
    @GET("v1/api/tim-kiem")
    Call<SearchResponse> getSearch(@Query("keyword") String query);
    @GET("v1/api/danh-sach/phim-le")
    Call<IndividualFilmResponse> getIndividualFilm(@Query("page") int page);
    @GET("v1/api/danh-sach/phim-bo")
    Call<IndividualFilmResponse> getSeriesFilm(@Query("page") int page);
    @GET("v1/api/danh-sach/hoat-hinh")
    Call<IndividualFilmResponse> getAnimesFilm(@Query("page") int page);
    @GET("v1/api/danh-sach/tv-shows")
    Call<IndividualFilmResponse> getTVShowsFilm(@Query("page") int page);

}
