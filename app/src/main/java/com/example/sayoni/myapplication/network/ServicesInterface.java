package com.example.sayoni.myapplication.network;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServicesInterface {

    @GET(Endpoints.LIST_ARTICLES)
    Call<ArticleListResponse> getArticles();



}
