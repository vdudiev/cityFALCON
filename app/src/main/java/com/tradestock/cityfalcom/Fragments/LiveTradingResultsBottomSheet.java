package com.tradestock.cityfalcom.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tradestock.cityfalcom.Models.SignalsBuySellArticle;
import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.Networking.RetrofitCreate;
import com.tradestock.cityfalcom.Adapters.SignalFromBuySellArticleAdapter;
import com.tradestock.cityfalcom.Models.SignalsArticle;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveTradingResultsBottomSheet extends BottomSheetDialogFragment {
    RegistrationResponse registrationResponse;
    Integer buyCount;
    Integer sellCount;
    private TextView total_buy_signals;
    private TextView total_sell_signals;

    private TextView textView_profit_or_loss_for_buy;
    private TextView textView_profit_or_loss_for_sell;

    private TextView textView_buy_count_percent;
    private TextView textView_sell_count_percent;

    private Float sell_count_percent;
    private Float buy_count_percent ;

//    private Float sell_signal_percent;
//    private Float buy_signal_percent;


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

        sell_count_percent = 0f;
        buy_count_percent  = 0f;

        total_buy_signals = root.findViewById(R.id.textView_total_buy_signals);
        total_sell_signals = root.findViewById(R.id.textView_total_sell_signals);
        textView_buy_count_percent = root.findViewById(R.id.textView_buy_count_percent);
        textView_sell_count_percent = root.findViewById(R.id.textView_sell_count_percent);
        textView_profit_or_loss_for_buy = root.findViewById(R.id.textView_profit_or_loss_for_buy_on_live_trading_res);
        textView_profit_or_loss_for_sell = root.findViewById(R.id.textView_profit_or_loss_for_sell_on_live_trading_res);
        registrationResponse = new RegistrationResponse();

        RetrofitCreate.getRetrofit().GetSignalsBuySell(registrationResponse.getAccept(),
                registrationResponse.getAuthorization(),
                "",
                registrationResponse.getLang(),
                "").enqueue(new Callback<SignalsArticle>() {
            @SuppressLint({"SetTextI18n", "DefaultLocale"})
            @Override
            public void onResponse(Call<SignalsArticle> call, Response<SignalsArticle> response) {

                //data for buy
                SignalFromBuySellArticleAdapter adapter = new SignalFromBuySellArticleAdapter(response.body().getBuy(),getActivity());
                buyCount = adapter.getItemCount();
                for (Integer i = 0; i < adapter.getItemCount();i++){
                    float perDif = 0;
                    SignalsBuySellArticle currentArticleData = response.body().getBuy().get(i);
                    if(currentArticleData.getOrder().equals("buy")) {
                        if (currentArticleData.getPrice() > currentArticleData.getCurrent_price()) {
                            perDif = 100 - ((currentArticleData.getPrice() / currentArticleData.getCurrent_price()) * 100);
                        } else {
                            perDif = Math.abs(100 - ((currentArticleData.getCurrent_price() / currentArticleData.getPrice()) * 100));
                        }
                    } else {
                        if (currentArticleData.getPrice() > currentArticleData.getCurrent_price()) {
                            perDif = Math.abs(100 - ((currentArticleData.getPrice() / currentArticleData.getCurrent_price()) * 100));
                        } else {
                            perDif = 100 - ((currentArticleData.getCurrent_price() / currentArticleData.getPrice()) * 100);
                        }
                    }
                    buy_count_percent += perDif;
                }
                total_buy_signals.setText(buyCount.toString());
                textView_buy_count_percent.setText(String.format("%.2f",buy_count_percent) + "%");

                if (buy_count_percent < 0 ){
                    textView_buy_count_percent.setTextColor(getResources().getColor(R.color.colorLoss));
                    textView_profit_or_loss_for_buy.setText("Loss");
                    textView_profit_or_loss_for_buy.setBackgroundColor(getResources().getColor(R.color.colorLoss));
                }
                else if(buy_count_percent > 0){
                    textView_buy_count_percent.setTextColor(getResources().getColor(R.color.colorProfit));
                    textView_profit_or_loss_for_buy.setText("Profit");
                    textView_profit_or_loss_for_buy.setBackgroundColor(getResources().getColor(R.color.colorProfit));
                }


                //data for sell
                adapter = new SignalFromBuySellArticleAdapter(response.body().getSell(),getActivity());
                sellCount = adapter.getItemCount();
                for (Integer i = 0; i < adapter.getItemCount();i++){
                    float perDif = 0;
                    SignalsBuySellArticle currentArticleData = response.body().getSell().get(i);
                    if(currentArticleData.getOrder().equals("buy")) {
                        if (currentArticleData.getPrice() > currentArticleData.getCurrent_price()) {
                            perDif = 100 - ((currentArticleData.getPrice() / currentArticleData.getCurrent_price()) * 100);
                        } else {
                            perDif = Math.abs(100 - ((currentArticleData.getCurrent_price() / currentArticleData.getPrice()) * 100));
                        }
                    } else {
                        if (currentArticleData.getPrice() > currentArticleData.getCurrent_price()) {
                            perDif = Math.abs(100 - ((currentArticleData.getPrice() / currentArticleData.getCurrent_price()) * 100));
                        } else {
                            perDif = 100 - ((currentArticleData.getCurrent_price() / currentArticleData.getPrice()) * 100);
                        }
                    }
                    sell_count_percent += perDif;
                }

                total_sell_signals.setText(sellCount.toString());
                textView_sell_count_percent.setText(String.format("%.2f",sell_count_percent) + "%");

                if (sell_count_percent < 0 ){
                    textView_sell_count_percent.setTextColor(getResources().getColor(R.color.colorLoss));
                    textView_profit_or_loss_for_sell.setText("Loss");
                    textView_profit_or_loss_for_sell.setBackgroundColor(getResources().getColor(R.color.colorLoss));
                }
                else if(sell_count_percent > 0){
                    textView_sell_count_percent.setTextColor(getResources().getColor(R.color.colorProfit));
                    textView_profit_or_loss_for_sell.setText("Profit");
                    textView_profit_or_loss_for_sell.setBackgroundColor(getResources().getColor(R.color.colorProfit));
                }

            }

            @Override
            public void onFailure(Call<SignalsArticle> call, Throwable t) {

            }
        });
        return root;
    }
}
