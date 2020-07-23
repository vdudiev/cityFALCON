package com.example.cityfalcon;

import android.content.Context;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

class CountOfShortTermTrading {

    private RegistrationResponse registrationResponse = new RegistrationResponse();
    private RetrofitCreate retrofitCreate = new RetrofitCreate();
    private Integer signalsCount = 0;

    String getCountOfShortTermTrading(Context context) {

        Retrofit retrofit = retrofitCreate.getRetrofit();
        //first sell signals
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.GetSignalsBuySell(registrationResponse.getAccept(),registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {

            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy().getList(),context);
                signalsCount += adapter.getItemCount();
                adapter = new SignalFromBuySellArticleAdapter(response.body().getSell().getList(),context);
                signalsCount += adapter.getItemCount();
            }

            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {

            }
        });

        return signalsCount.toString();
    }
}
