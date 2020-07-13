package com.example.cityfalcon;


import android.content.Context;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignalFragment extends Fragment {


    public SignalFragment() {
        // Required empty public constructor
    }

    Retrofit retrofit;
    RegistrationResponse registrationResponse = new RegistrationResponse();
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signal, container, false);


        recyclerView = root.findViewById(R.id.recyclerview_signals_on_signals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Context context = getActivity();

        LinearLayout sellLinearLayout = root.findViewById(R.id.LinearLayout_sell_signal_fragment);
        LinearLayout buyLinearLayout = root.findViewById(R.id.LinearLayout_buy_signal_fragment);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://msofter.com/tradestocks2/public/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        sellLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                buyLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorWhite));

                ApiServiceSignalsOnSignals apiServiceSignalsOnSignals = retrofit.create(ApiServiceSignalsOnSignals.class);
                apiServiceSignalsOnSignals.ArticleAdapter(registrationResponse.getAccept(),registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getSell().getList(),context);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onFailure(Call<SignalsArticle> call, Throwable t) {

                    }
                });
            }
        });

        buyLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                sellLinearLayout.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.colorWhite));

                ApiServiceSignalsOnSignals apiServiceSignalsOnSignals = retrofit.create(ApiServiceSignalsOnSignals.class);
                apiServiceSignalsOnSignals.ArticleAdapter(registrationResponse.getAccept(),registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {
                    @Override
                    public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                        SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy().getList(),context);
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
