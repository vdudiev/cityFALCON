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
    private Integer signalsCount = 0;
    TextView textViewSignalCount;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_signal, container, false);


        recyclerView = root.findViewById(R.id.recyclerview_signals_on_signals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        Context context = getActivity();

        LinearLayout sellLinearLayout = root.findViewById(R.id.LinearLayout_sell_signal_fragment);
        LinearLayout buyLinearLayout = root.findViewById(R.id.LinearLayout_buy_signal_fragment);


      RetrofitCreate retrofitCreate = new RetrofitCreate();
      retrofit = retrofitCreate.getRetrofit();
        //first sell signals
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.GetSignalsBuySell(registrationResponse.getAccept(),registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy().getList(),context);
                signalsCount += adapter.getItemCount();
                adapter = new SignalFromBuySellArticleAdapter(response.body().getSell().getList(),context);
                signalsCount += adapter.getItemCount();
                textViewSignalCount.setText(signalsCount.toString());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {

            }
        });

        textViewSignalCount = root.findViewById(R.id.textview_number_of_short_terms_signal_fragment);
       /* CountOfShortTermTrading countOfShortTermTrading = new CountOfShortTermTrading();
        textViewSignalCount.setText(countOfShortTermTrading.getCountOfShortTermTrading(getActivity()));*/


        sellLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bottom_border_for_sell_or_buy));
                buyLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));

                ApiService apiService = retrofit.create(ApiService.class);
                apiService.GetSignalsBuySell(registrationResponse.getAccept(),registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {
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
                sellLinearLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.background_with_shadow_for_selector_signals_fragment));

                ApiService apiService = retrofit.create(ApiService.class);
                apiService.GetSignalsBuySell(registrationResponse.getAccept(),registrationResponse.getAuthorization()).enqueue(new Callback<SignalsArticle>() {
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
