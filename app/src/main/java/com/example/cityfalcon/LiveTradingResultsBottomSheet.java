package com.example.cityfalcon;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LiveTradingResultsBottomSheet extends BottomSheetDialogFragment {
    Retrofit retrofit;
    RegistrationResponse registrationResponse;
    Integer buyCount;
    Integer sellCount;
    private TextView total_buy_signals;
    private TextView total_sell_signals;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_live_trading_results_bottom_sheet, container, false);
        Button goToSignalsButton = root.findViewById(R.id.button_go_to_signals_bottom_sheet);
        goToSignalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        total_buy_signals = root.findViewById(R.id.textView_total_buy_signals);
        total_sell_signals = root.findViewById(R.id.textView_total_sell_signals);
        registrationResponse = new RegistrationResponse();

        RetrofitCreate retrofitCreate = new RetrofitCreate();
        retrofit = retrofitCreate.getRetrofit();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.GetSignalsBuySell(registrationResponse.getAccept(),
                registrationResponse.getAuthorization(),
                "",
                registrationResponse.getLang()).enqueue(new Callback<SignalsArticle>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {
                SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy().getList(),getActivity());
                buyCount = adapter.getItemCount();
                adapter = new SignalFromBuySellArticleAdapter(response.body().getSell().getList(),getActivity());
                sellCount = adapter.getItemCount();
                total_buy_signals.setText(buyCount.toString());
                total_sell_signals.setText(sellCount.toString());
            }

            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {

            }
        });
        return root;
    }
}
