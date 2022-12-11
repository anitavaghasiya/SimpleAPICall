package com.photo.ai.restapi.rest;


import com.photo.ai.restapi.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("us/33162")
    Call<Example> getCollectionData();

}
