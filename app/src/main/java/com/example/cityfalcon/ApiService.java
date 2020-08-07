package com.example.cityfalcon;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiService {
    //signals on signals
    @FormUrlEncoded
    @POST("signals2")
    Call<SignalsArticle> GetSignalsBuySell (@Header("Accept") String accept,
                                            @Header("Authorization") String authorization,
                                            @Field("filters") String filters,
                                            @Field("lang") String lang,
                                            @Field("search") String search);

    @FormUrlEncoded
    @POST("signal")
    Call<SignalsMoreArticle> GetMoreAboutSignal (@Header("Accept") String accept,
                                                 @Header("Authorization")String authorization,
                                                 @Field("signal_id") Float signal_id,
                                                 @Field("lang") String lang);

    //watch list
    @GET("watchlist")
    Call<SignalsArticle> GetWatchList   (@Header("Accept") String accept,
                                         @Header("Authorization") String authorization);

    @POST("sectors")
    Call<SectorsArticle> GetSectors (@Header("Accept") String accept,
                                     @Header("Authorization") String authorization);

    @GET("sectors/{sector}")
    Call<SignalsArticle> GetSectorsByID (@Header("Accept") String accept,
                                         @Header("Authorization") String authorization,
                                         @Path(value = "sector", encoded = true) Integer sector);

    @FormUrlEncoded
    @POST("watchlist-delete")
    Call<AddedAndDeletedSignalIdArticle> DeleteSignalFromWatchList (@Header("Accept") String accept,
                                                    @Header("Authorization") String authorization,
                                                    @Field("signal_id") Integer signal_id);

    @FormUrlEncoded
    @POST("watchlist-add")
    Call<AddedAndDeletedSignalIdArticle> addSignalToWatchList (@Header("Accept") String accept,
                               @Header("Authorization") String authorization,
                               @Field("signal_id") Integer signal_id);

    /*@FormUrlEncoded
    @POST("search")
    Call<SignalsBuySellArticle> GetSignalsFromSearch(@Header("Accept") String accept,
                                                 @Header("Authorization") String authorization,
                                                 @Field("search") String search,
                                                 @Field("instrument") String instrument);*/

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
