package com.example.cityfalcon;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiService {
    //signals on signals
    @POST("signals")
    Call<SignalsArticle> GetSignalsBuySell (@Header("Accept") String accept,
                                            @Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("signal")
    Call<SignalsMoreArticle> GetMoreAboutSignal (@Header("Accept") String accept,
                                                 @Header("Authorization")String authorization,
                                                 @Field("signal_id") Float signal_id,
                                                 @Field("lang") String lang);

    //watch list
    @POST("watchlist-get")
    Call<SignalsArticle> GetWatchList   (@Header("Accept") String accept,
                                         @Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("watchlist-delete")
    Call<AddedAndDeletedSignalIdArticle> DeleteSignalFromWatchList (@Header("Accept") String accept,
                                                    @Header("Authorization") String authorization,
                                                    @Field("signal_id") Float signal_id);

    @FormUrlEncoded
    @POST("watchlist-add")
    Call<AddedAndDeletedSignalIdArticle> addSignalToWatchList (@Header("Accept") String accept,
                               @Header("Authorization") String authorization,
                               @Field("signal_id") Float signal_id);

    @FormUrlEncoded
    @POST("search")
    Call<SignalsBuySellArticle> GetSignalsFromSearch(@Header("Accept") String accept,
                                                 @Header("Authorization") String authorization,
                                                 @Field("search") String search,
                                                 @Field("instrument") String instrument);

    @FormUrlEncoded
    @POST("instrument")
    Call<InstrumentArticle> GetInstrumentId(@Header("Accept") String accept,
                                            @Header("Authorization") String authorization,
                                            @Field("instrument") String instrument);

    @FormUrlEncoded
    @POST("watchlist-exists")
    Call<Integer> CheckSignalWatchList(@Header("Accept") String accept,
                                       @Header("Authorization") String authorization,
                                       @Field("signal_id") Float signal_id);



}
