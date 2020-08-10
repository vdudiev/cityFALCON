package com.tradestock.cityfalcom.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.Networking.RetrofitCreate;
import com.tradestock.cityfalcom.Adapters.SignalFromBuySellArticleAdapter;
import com.tradestock.cityfalcom.Models.SignalsArticle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class WatchlistFragment extends Fragment {

    public WatchlistFragment() {

    }

    private RegistrationResponse registrationResponse = new RegistrationResponse();
    private RecyclerView recyclerView;
    private String filters = "";
    private int shownTab=0;

    private Integer countOfShortTermsOnWatchlist;

    private SwipeRefreshLayout srl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_watchlist, container, false);
        srl = root.findViewById(R.id.srl);
        Context context = getActivity();
        LinearLayout sellLinearLayoutWatchList = root.findViewById(R.id.LinearLayout_sell_watchlist_fragment);
        LinearLayout buyLinearLayoutWatchLis = root.findViewById(R.id.LinearLayout_buy_watchlist_fragment);
        recyclerView = root.findViewById(R.id.recyclerview_signals_on_watchlist);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        TextView textview_number_of_short_terms = root.findViewById(R.id.textview_number_of_short_terms);
        Button button_to_open_filters_from_watchlist = root.findViewById(R.id.button_to_open_filters_from_watchlist);
        button_to_open_filters_from_watchlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiltersForSignlsBottomSheet filtersForSignlsBottomSheet = new FiltersForSignlsBottomSheet();
                filtersForSignlsBottomSheet.setOnFilterListener(new FiltersForSignlsBottomSheet.OnFilterListener() {
                    @Override
                    public void onFilter(String filters) {
                        WatchlistFragment.this.filters = filters;
                        if (shownTab == 0){
                            sellLinearLayoutWatchList.callOnClick();
                        }
                        else {
                            buyLinearLayoutWatchLis.callOnClick();
                        }
                    }
                });
                filtersForSignlsBottomSheet.show(getFragmentManager(),"filtersForSignlsBottomSheet");
            }
        });

        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (shownTab == 0){
                    sellLinearLayoutWatchList.callOnClick();
                }
                else {
                    buyLinearLayoutWatchLis.callOnClick();
                }
            }
        });


        //first sell signals
        countOfShortTermsOnWatchlist = 0;
        srl.setRefreshing(true);
        RetrofitCreate.getRetrofit().GetWatchList(registrationResponse.getAccept(),
                registrationResponse.getAuthorization(),
                filters).enqueue(new Callback<SignalsArticle>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy(),context);
                countOfShortTermsOnWatchlist += adapter.getItemCount();
                adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),context);
                countOfShortTermsOnWatchlist += adapter.getItemCount();
                textview_number_of_short_terms.setText(countOfShortTermsOnWatchlist.toString());
                recyclerView.setAdapter(adapter);
                srl.setRefreshing(false);
            }
            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {

            }
        });

        //BUTTONS SELL AND BUY
        sellLinearLayoutWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shownTab=0;
                sellLinearLayoutWatchList.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                buyLinearLayoutWatchLis.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));
                srl.setRefreshing(true);
                RetrofitCreate.getRetrofit().GetWatchList(registrationResponse.getAccept(),
                        registrationResponse.getAuthorization(),
                        filters).enqueue(new Callback<SignalsArticle>() {

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        srl.setRefreshing(false);
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),context);
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
                shownTab=1;
                buyLinearLayoutWatchLis.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                sellLinearLayoutWatchList.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));
                srl.setRefreshing(true);

                RetrofitCreate.getRetrofit().GetWatchList(registrationResponse.getAccept(),
                        registrationResponse.getAuthorization(),
                        filters
                        ).enqueue(new Callback<SignalsArticle>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        srl.setRefreshing(false);
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy(),context);
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
