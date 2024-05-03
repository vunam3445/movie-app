package com.example.moviesapp.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.jsonResponse.MoviesNewUpdateResponse;
import com.example.moviesapp.jsonResponse.SearchResponse;
import com.example.moviesapp.model.Item;
import com.example.moviesapp.retrofit.ApiServer;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchViewModel extends ViewModel {
    private MutableLiveData<List<Item>> movies = new MutableLiveData<>();
    public LiveData<List<Item>> getSearch(){
        return movies;
    }
    public void fetchSearch(String query,int n,int t) {

        ApiServer.ApiService.getSearch(query)
                .enqueue(new Callback<SearchResponse>() {
                    @Override
                    public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                        if(response.isSuccessful()){
                            SearchResponse searchResponse = response.body();
                            List<Item> fullItemList = searchResponse.getData().getItems();
                            // Kiểm tra nếu danh sách có ít nhất 20 phần tử
                            if (fullItemList.size() >= 20) {
                                // Lấy 20 phần tử đầu tiên
                                List<Item> sublist = fullItemList.subList(t, n);
                                // Gán danh sách con này cho LiveData
                                movies.setValue(sublist);
                            } else {
                                // Nếu danh sách ít hơn 20 phần tử, gán toàn bộ danh sách cho LiveData
                                movies.setValue(fullItemList);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SearchResponse> call, Throwable t) {
                        Log.e("SearchViewModelError",t.getMessage());
                    }
                });
    }


}
