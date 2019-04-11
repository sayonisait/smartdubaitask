package com.example.sayoni.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.example.sayoni.myapplication.network.ArticleListResponse;
import com.example.sayoni.myapplication.network.RestClient;
import com.example.sayoni.myapplication.network.ServicesInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ListAdapter.onItemSelectionInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView= findViewById(R.id.recycler_view);
       final RelativeLayout progressbar= findViewById(R.id.progress_circular);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        ServicesInterface apiInterface = RestClient.getClient().create(ServicesInterface.class);
        /**
         GET List Resources
         **/
        Call<ArticleListResponse> call = apiInterface.getArticles();
        progressbar.setVisibility(View.VISIBLE);
        call.enqueue(new Callback<ArticleListResponse>() {
            @Override
            public void onResponse(Call<ArticleListResponse> call, Response<ArticleListResponse> response) {
                if(response.isSuccessful()) {
                    assert response.body() != null;
                    ListAdapter mAdapter = new ListAdapter(response.body().results, MainActivity.this, MainActivity.this);
                    recyclerView.setAdapter(mAdapter);
                }

                progressbar.setVisibility(View.GONE);


        }

            @Override
            public void onFailure(Call<ArticleListResponse> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
                progressbar.setVisibility(View.GONE);
                if(t instanceof IOException){
                    Toast.makeText(MainActivity.this,"Please check your internet connection", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this,"Server error", Toast.LENGTH_SHORT).show();

                }
            }
        });



    }

    @Override
    public void onItemClick(ArticleListResponse.Result selectedItem) {
        Intent i = new Intent(this, DetailActivity.class);
        i.putExtra("url",selectedItem.url);
         startActivity(i);
    }
}
