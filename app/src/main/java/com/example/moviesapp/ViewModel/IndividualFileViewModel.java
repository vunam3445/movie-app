//package com.example.moviesapp.ViewModel;
//
//import android.util.Log;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.moviesapp.jsonResponse.IndividualFilmResponse;
//import com.example.moviesapp.model.Item;
//import com.example.moviesapp.retrofit.ApiServer;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class IndividualFileViewModel extends ViewModel {
//    private MutableLiveData <List<Item>> movies = new MutableLiveData<>();
//    public LiveData<List<Item>> getIndividualFilm(){
//        return movies;
//    }
//    public void fetchIndividual(){
//        ApiServer.ApiService.getIndividualFilm()
//                .enqueue(new Callback<IndividualFilmResponse>() {
//                    @Override
//                    public void onResponse(Call<IndividualFilmResponse> call, Response<IndividualFilmResponse> response) {
//                        if(response.isSuccessful()){
//                            IndividualFilmResponse individualFilmResponse = response.body();
//                            if(individualFilmResponse != null && individualFilmResponse.getStatus().equals("success")){
//                                movies.setValue(individualFilmResponse.getData().getItems());
//                                Log.e("IndividualFileViewModel","success");
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<IndividualFilmResponse> call, Throwable t) {
//                        Log.e("IndividualFileViewModel",t.getMessage());
//                    }
//                });
//    }
//}
