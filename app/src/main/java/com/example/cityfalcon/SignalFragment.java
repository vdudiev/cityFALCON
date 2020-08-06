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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignalFragment extends Fragment {


    public SignalFragment() {

    }

    private Retrofit retrofit;
    private RegistrationResponse registrationResponse = new RegistrationResponse();
    private RecyclerView recyclerView;
    private Integer signalsCount;
    private TextView textViewSignalCount;
    private String filters = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signal, container, false);

        Button buttonOnSignalFragmentToGetFilters = root.findViewById(R.id.button_on_signal_fragment_to_get_filters);
        buttonOnSignalFragmentToGetFilters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FiltersForSignlsBottomSheet filtersForSignlsBottomSheet = new FiltersForSignlsBottomSheet();
                filtersForSignlsBottomSheet.show(getFragmentManager(),"filtersForSignlsBottomSheet");
            }
        });

        recyclerView = root.findViewById(R.id.recyclerview_signals_on_signals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Context context = getActivity();

        LinearLayout sellLinearLayout = root.findViewById(R.id.LinearLayout_sell_signal_fragment);
        LinearLayout buyLinearLayout = root.findViewById(R.id.LinearLayout_buy_signal_fragment);


        //first sell signals
        signalsCount = 0;
        RetrofitCreate.getRetrofit().GetSignalsBuySell(registrationResponse.getAccept(),
                registrationResponse.getAuthorization(),
                filters,
                registrationResponse.getLang()).enqueue(new Callback<SignalsArticle>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy(),context);
                signalsCount += response.body().buy.size()+response.body().sell.size();
                adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),context);
//                signalsCount += adapter.getItemCount();
                textViewSignalCount.setText(signalsCount.toString());
                adapter.setSellBuyChek(0);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {

            }
        });

        textViewSignalCount = root.findViewById(R.id.textview_number_of_short_terms_signal_fragment);


        sellLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                buyLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));

                RetrofitCreate.getRetrofit().GetSignalsBuySell(registrationResponse.getAccept(),
                        registrationResponse.getAuthorization(),
                        filters,
                        registrationResponse.getLang()).enqueue(new Callback<SignalsArticle>() {
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),context);
                        adapter.setSellBuyChek(0);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<SignalsArticle> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }
        });

        buyLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                sellLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));

                RetrofitCreate.getRetrofit().GetSignalsBuySell(registrationResponse.getAccept(),
                        registrationResponse.getAuthorization(),
                        filters,
                        registrationResponse.getLang()).enqueue(new Callback<SignalsArticle>() {
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy(),context);
                        adapter.setSellBuyChek(1);
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
