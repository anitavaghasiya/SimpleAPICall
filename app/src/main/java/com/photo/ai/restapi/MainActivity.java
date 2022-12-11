package com.photo.ai.restapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.photo.ai.restapi.adapter.LibraryAdapter;
import com.photo.ai.restapi.common.BaseActivity;
import com.photo.ai.restapi.model.Example;
import com.photo.ai.restapi.model.Place;
import com.photo.ai.restapi.rest.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_latest;
    LibraryAdapter latestAdapter;
    List<Place> latestDataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_latest =findViewById(R.id.rv_latest);
        if (BaseActivity.isConnectingToInternet(MainActivity.this)) {
            rv_latest.setVisibility(View.VISIBLE);

            getLatestData();
        } else {
            rv_latest.setVisibility(View.GONE);
            Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

        }
    }

    public void getLatestData() {
        API.apiInterface().getCollectionData().enqueue(new retrofit2.Callback<Example>() {
            @Override
            public void onResponse(@NonNull Call<Example> call, @NonNull Response<Example> response) {
                Example libraryData = response.body();
                assert libraryData != null;
                if (libraryData.getPlaces() != null) {

                    latestDataList = libraryData.getPlaces();
                    if (latestDataList != null && latestDataList.size() > 0) {
                        rv_latest.setVisibility(View.VISIBLE);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
                        latestAdapter = new LibraryAdapter(MainActivity.this, latestDataList,"");
                        rv_latest.setLayoutManager(linearLayoutManager);
                        rv_latest.setItemAnimator(new DefaultItemAnimator());
                        rv_latest.setAdapter(latestAdapter);
                    } else {
                        rv_latest.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Example> call, @NonNull Throwable t) {
                rv_latest.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("collection", "onFailure: " + t.getMessage());
            }
        });
    }

}