package com.tradestock.cityfalcom.Networking;

import com.grapesnberries.curllogger.CurlLoggerInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitCreate {

    private static OkHttpClient.Builder client = new OkHttpClient.Builder()
            .addInterceptor(new CurlLoggerInterceptor());

    private static Retrofit retrofit;
    private static ApiService service = null;
    private static void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://msofter.com/tradestocks2/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();
        service = retrofit.create(ApiService.class);
    }

    public static ApiService getRetrofit() {
        if (service == null){
            initRetrofit();
        }
        return service;
    }
}
