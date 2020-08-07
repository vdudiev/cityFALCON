package com.example.cityfalcon;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class SignalDetails extends Fragment {


    private Context context;
    private SignalsBuySellArticle signal_to_set;

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

        TextView signals_details_symbol_up = root.findViewById(R.id.textView_signals_details_symbol_up);
        TextView signals_details_symbol_mid = root.findViewById(R.id.textView_signal_symbol_on_signals_details_mid);
        TextView signal_date_time_on_signals_details = root.findViewById(R.id.textView_signal_date_time_on_signals_details);
        TextView signal_price_on_signals_details = root.findViewById(R.id.textView_signal_price_on_signals_details);
        TextView signal_current_price_on_signals_details = root.findViewById(R.id.textView_signal_current_price_on_signals_details);
        TextView signal_percentage_difference_on_signals_details = root.findViewById(R.id.textView_signal_percentage_difference_on_signals_details);
        TextView sector_title_signals_details = root.findViewById(R.id.textView_sector_title_signals_details);
        TextView stop_loss_on_signals_details = root.findViewById(R.id.textView_stop_loss_on_signals_details);
        TextView profit_on_signals_details = root.findViewById(R.id.textView_take_profit_on_signals_details);


        signals_details_symbol_up.setText(signal_to_set.getSymbol());
        signals_details_symbol_mid.setText(signal_to_set.getSymbol());
        signal_date_time_on_signals_details.setText(signal_to_set.getDate_time());
        signal_price_on_signals_details.setText(signal_to_set.getPrice().toString());
        signal_current_price_on_signals_details.setText(signal_to_set.getCurrent_price().toString());
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
                context = getActivity();
                FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                SignalFragment signalFragment = new SignalFragment();
                fragmentTransaction.replace(R.id.content_fragment,signalFragment );
                fragmentTransaction.commit();
            }
        });

        return root;
    }






}
