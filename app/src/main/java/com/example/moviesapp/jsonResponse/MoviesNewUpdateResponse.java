package com.example.moviesapp.jsonResponse;


import java.util.List;

import com.example.moviesapp.model.ItemsMoviesNewUpdate;
import com.example.moviesapp.model.Pagination;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MoviesNewUpdateResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("items")
    @Expose
    private List<ItemsMoviesNewUpdate> items;
    @SerializedName("pagination")
    @Expose
    private Pagination pagination;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ItemsMoviesNewUpdate> getItems() {
        return items;
    }

    public void setItems(List<ItemsMoviesNewUpdate> items) {
        this.items = items;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
@Override
    public String toString(){
return "Response{"+
    "status:"+status+
        "items:"+items+
        "parination:"+pagination+"}";
}

}

