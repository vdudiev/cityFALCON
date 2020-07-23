package com.example.cityfalcon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    //signals on signals
    @POST("signals")
    Call<SignalsArticle> GetSignalsBuySell (@Header("Accept") String accept, @Header("Authorization") String authorization);

    @POST("signal")
    Call<SignalsMoreArticle> GetMoreAboutSignal (@Header("Accept") String accept, @Header("Authorization")String authorization, @Body Float signal_id, @Body String lang);

    //watch list
    @POST("watchlist-get")
    Call<SignalsArticle> getWatchList(@Header("Accept") String accept, @Header("Authorization") String authorization);

    @POST("watchlist-delete")
    Call<SignalsArticle> deleteSignalFromWatchList (@Header("Accept") String accept, @Header("Authorization") String authorization, @Body Float signal_id);

    @POST("watchlist-add")
    Call<SignalsArticle> addSignalToWatchList (@Header("Accept") String accept, @Header("Authorization") String authorization,  @Body Float signal_id);


}
