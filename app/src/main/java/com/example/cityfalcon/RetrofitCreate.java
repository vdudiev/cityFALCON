package com.example.cityfalcon;

import com.grapesnberries.curllogger.CurlLoggerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreate {

    OkHttpClient.Builder client = new OkHttpClient.Builder()
            // add our curl logger here
            .addInterceptor(new CurlLoggerInterceptor());
    Retrofit retrofit;
    public RetrofitCreate() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://msofter.com/tradestocks2/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
