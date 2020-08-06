package com.example.cityfalcon;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EdgeEffect;
import android.widget.LinearLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class WatchlistFragment extends Fragment {

    public WatchlistFragment() {

    }

    private RegistrationResponse registrationResponse = new RegistrationResponse();
    private RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_watchlist, container, false);

        Context context = getActivity();
        LinearLayout sellLinearLayoutWatchList = root.findViewById(R.id.LinearLayout_sell_watchlist_fragment);
        LinearLayout buyLinearLayoutWatchLis = root.findViewById(R.id.LinearLayout_buy_watchlist_fragment);
        recyclerView = root.findViewById(R.id.recyclerview_signals_on_watchlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //first sell signals
        RetrofitCreate.getRetrofit().GetWatchList(registrationResponse.getAccept(),
                                registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {

            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),context);
                adapter.setSellBuyChek(0);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {

            }
        });

        //BUTTONS SELL AND BUY
        sellLinearLayoutWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellLinearLayoutWatchList.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                buyLinearLayoutWatchLis.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));

                RetrofitCreate.getRetrofit().GetWatchList(registrationResponse.getAccept(),registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),context);
                        adapter.setSellBuyChek(0);
                        recyclerView.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(Call<SignalsArticle> call, Throwable t) {

                    }
                });
            }
        });

        buyLinearLayoutWatchLis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyLinearLayoutWatchLis.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                sellLinearLayoutWatchList.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));


                RetrofitCreate.getRetrofit().GetWatchList(registrationResponse.getAccept(),registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy(),context);
                        adapter.setSellBuyChek(0);
                        recyclerView.setAdapter(adapter);
                    }
                    @Override
                    public void onFailure(Call<SignalsArticle> call, Throwable t) {

                    }
                });
            }
        });


        return root;

    }

}
