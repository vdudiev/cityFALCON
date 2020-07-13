package com.example.cityfalcon;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiServiceSignalsOnSignals {
    @POST("signals")
    Call<SignalsArticle> ArticleAdapter (@Header("Accept") String accept, @Header("Authorization") String authorization);
}
