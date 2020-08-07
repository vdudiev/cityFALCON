package com.example.cityfalcon.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityfalcon.Networking.RegistrationResponse;
import com.example.cityfalcon.Networking.RetrofitCreate;
import com.example.cityfalcon.Models.AddedAndDeletedSignalIdArticle;
import com.example.cityfalcon.R;
import com.example.cityfalcon.Models.SignalsBuySellArticle;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignalDetails extends Fragment {


    private Context context;
    private SignalsBuySellArticle signal_to_set;
    private RegistrationResponse registrationResponse = new RegistrationResponse();


    public SignalsBuySellArticle getSignal_to_set() {
        return signal_to_set;
    }

    public void setSignal_to_set(SignalsBuySellArticle signal_to_set) {
        this.signal_to_set = signal_to_set;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signal_details, container, false);
        Button buttonBackToSignals = root.findViewById(R.id.bottom_back_to_signals_from_details);
        Button buttonAddToWatchList = root.findViewById(R.id.btn_add_to_watchlist);
        if(signal_to_set.getWatchlist()==0){
            buttonAddToWatchList.setText(R.string.string_add_to_watchlist);
        } else {
            buttonAddToWatchList.setText(R.string.string_remove_from_watchlist);
        }
        buttonAddToWatchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (signal_to_set.getWatchlist()==0) {
                    RetrofitCreate.getRetrofit().addSignalToWatchList(registrationResponse.getAccept(),
                            registrationResponse.getAuthorization(),
                            signal_to_set.getId()).enqueue(new Callback<AddedAndDeletedSignalIdArticle>() {
                        @Override
                        public void onResponse(Call<AddedAndDeletedSignalIdArticle> call, Response<AddedAndDeletedSignalIdArticle> response) {
                            buttonAddToWatchList.setText(R.string.string_remove_from_watchlist);
                            signal_to_set.setWatchlist(1);
                            Toast.makeText(getContext(), "Added to your watchlist", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<AddedAndDeletedSignalIdArticle> call, Throwable t) {
                            Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else {
                    RetrofitCreate.getRetrofit().DeleteSignalFromWatchList(registrationResponse.getAccept(),
                            registrationResponse.getAuthorization(),
                            signal_to_set.getId()).enqueue(new Callback<AddedAndDeletedSignalIdArticle>() {
                        @Override
                        public void onResponse(Call<AddedAndDeletedSignalIdArticle> call, Response<AddedAndDeletedSignalIdArticle> response) {
                            buttonAddToWatchList.setText(R.string.string_add_to_watchlist);
                            signal_to_set.setWatchlist(0);
                            Toast.makeText(getContext(), "Removed from your watchlist", Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(Call<AddedAndDeletedSignalIdArticle> call, Throwable t) {
                            Toast.makeText(getContext(), t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });

        TextView signals_details_symbol_up = root.findViewById(R.id.textView_signals_details_symbol_up);
        TextView signals_details_symbol_mid = root.findViewById(R.id.textView_signal_symbol_on_signals_details_mid);
        TextView signal_date_time_on_signals_details = root.findViewById(R.id.textView_signal_date_time_on_signals_details);
        TextView signal_price_on_signals_details = root.findViewById(R.id.textView_signal_price_on_signals_details);
        TextView signal_current_price_on_signals_details = root.findViewById(R.id.textView_signal_current_price_on_signals_details);
        TextView signal_percentage_difference_on_signals_details = root.findViewById(R.id.textView_signal_percentage_difference_on_signals_details);
        TextView sector_title_signals_details = root.findViewById(R.id.textView_sector_title_signals_details);
        TextView stop_loss_on_signals_details = root.findViewById(R.id.textView_stop_loss_on_signals_details);
        TextView profit_on_signals_details = root.findViewById(R.id.textView_take_profit_on_signals_details);
        TextView tv_additional_detail = root.findViewById(R.id.tv_additional_detail);


        signals_details_symbol_up.setText(signal_to_set.getSymbol());
        signals_details_symbol_mid.setText(signal_to_set.getSymbol());
        Date date = new Date(System.currentTimeMillis()-24*60*60*1000);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        signal_date_time_on_signals_details.setText(sdf.format(date));
        signal_price_on_signals_details.setText(signal_to_set.getPrice().toString());
        signal_current_price_on_signals_details.setText(signal_to_set.getCurrent_price().toString());
        tv_additional_detail.setText(Html.fromHtml(signal_to_set.getDetailed_signal_info()));
        Float perDif = ((signal_to_set.getCurrent_price() - signal_to_set.getPrice())* 100)/ signal_to_set.getPrice();
        if (perDif > 0 ) {
            signal_percentage_difference_on_signals_details.setTextColor(getResources().getColor(R.color.colorProfit));
            signal_percentage_difference_on_signals_details.setText("+" + String.format("%.2f",perDif)+"%");
        }
        else {
            signal_percentage_difference_on_signals_details.setTextColor(getResources().getColor(R.color.colorLoss));
            signal_percentage_difference_on_signals_details.setText(String.format("%.2f",perDif)+"%");
        }
        sector_title_signals_details.setText(signal_to_set.getSector().getTitle());
        stop_loss_on_signals_details.setText("SL = " +  signal_to_set.getStop_loss());
        profit_on_signals_details.setText("TP = " +  signal_to_set.getTake_profit());

        buttonBackToSignals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        return root;
    }






}
