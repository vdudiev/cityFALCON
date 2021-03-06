package com.tradestock.cityfalcom.Networking;

import com.tradestock.cityfalcom.Models.AddedAndDeletedSignalIdArticle;
import com.tradestock.cityfalcom.Models.FiltersArticle;
import com.tradestock.cityfalcom.Models.InstrumentArticle;
import com.tradestock.cityfalcom.Models.Instruments;
import com.tradestock.cityfalcom.Models.Languages;
import com.tradestock.cityfalcom.Models.SectorsArticle;
import com.tradestock.cityfalcom.Models.SignalsArticle;
import com.tradestock.cityfalcom.Models.SignalsMoreArticle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    @FormUrlEncoded
    @POST("watchlist")
    Call<SignalsArticle> GetWatchList   (@Header("Accept") String accept,
                                         @Header("Authorization") String authorization,
                                         @Field("filter") String filter );

    @GET("instruments")
    Call<FiltersArticle> GetInstrumentsForFilters   (@Header("Accept") String accept,
                                                     @Header("Authorization") String authorization);
    @POST("sectors")
    Call<SectorsArticle> GetSectors (@Header("Accept") String accept,
                                     @Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("sectors/{sector}")
    Call<SignalsArticle> GetSectorsByID (@Header("Accept") String accept,
                                         @Header("Authorization") String authorization,
                                         @Path(value = "sector", encoded = true) Integer sector,
                                         @Field("search") String search,
                                         @Field("filter") String filter);

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

    @GET("instruments")
    Call<Instruments> GetInstruments(@Header("Accept") String accept,
                                     @Header("Authorization") String authorization);

    @FormUrlEncoded
    @POST("watchlist-exists")
    Call<Integer> CheckSignalWatchList(@Header("Accept") String accept,
                                       @Header("Authorization") String authorization,
                                       @Field("signal_id") Float signal_id);


    @GET("lang")
    Call<Languages> getLanguages(@Header("Accept") String accept,
                                 @Header("Authorization") String authorization);


    @GET("lang/{code}")
    Call<Languages> getLanguage(@Header("Accept") String accept,
                                @Header("Authorization") String authorization,
                                @Path(value = "code", encoded = true) String code);

}
