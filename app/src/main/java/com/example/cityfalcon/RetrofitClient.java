package com.example.cityfalcon;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient   {


    private static final String root_url = "http://msofter.com/tradestocks2/public/api/";

    private static Retrofit getRetrofitInstance(){
        return new Retrofit.Builder()
                .baseUrl(root_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    }
