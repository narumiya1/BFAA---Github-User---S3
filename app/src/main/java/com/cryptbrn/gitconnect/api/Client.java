package com.cryptbrn.gitconnect.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Client {
    public static Service getService(){
        Retrofit apiretrofit = new Retrofit.Builder()
                .baseUrl(" https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return apiretrofit.create(Service.class);
    }
}
