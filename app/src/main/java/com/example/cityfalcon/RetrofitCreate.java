package com.example.cityfalcon;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreate {
    Retrofit retrofit;
    public RetrofitCreate() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://msofter.com/tradestocks2/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
