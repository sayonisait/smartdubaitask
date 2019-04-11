package com.example.sayoni.myapplication.network;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * This class provides the implementation of Retrofit RestClient.
 */

public class RestClient {


    private static Retrofit retrofit = null;

  public   static Retrofit getClient() {


        OkHttpClient client = new OkHttpClient.Builder().build();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.nytimes.com")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();



        return retrofit;
    }







}
