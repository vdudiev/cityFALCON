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
    private Float signal_id_to_set;

    public void setSignal_id_to_set(Float signal_id_to_set) {
        this.signal_id_to_set = signal_id_to_set;
    }

    private Integer sellBuyChek;

    public void setSellBuyCheking(Integer sellBuyCheking) {
        sellBuyChek = sellBuyCheking;
    }

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
        TextView sector_title_signals_details = root.findViewById(R.id.textView_sector_title_signals_details);

        RegistrationResponse registrationResponse = new RegistrationResponse();

        RetrofitCreate retrofitCreate = new RetrofitCreate();
        Retrofit retrofit = retrofitCreate.getRetrofit();

        ApiService apiService = retrofit.create(ApiService.class);
        Float signal_id = signal_id_to_set;
        String lang = "en";
        apiService.GetMoreAboutSignal(registrationResponse.getAccept(),
                registrationResponse.getAuthorization(),
                signal_id, lang).enqueue(new Callback<SignalsMoreArticle>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<SignalsMoreArticle> call, Response<SignalsMoreArticle> response) {
                SignalsMoreArticle signalsMoreArticle = response.body();
                assert signalsMoreArticle != null;
                signals_details_symbol_up.setText(signalsMoreArticle.getSignal().getSymbol());
                signals_details_symbol_mid.setText(signalsMoreArticle.getSignal().getSymbol());
                signal_date_time_on_signals_details.setText(signalsMoreArticle.getSignal().getDate_time());
                if (sellBuyChek == 0) {
                    signal_price_on_signals_details.setText(signalsMoreArticle.getSignal().getSell_price().toString());
                }
                else {
                    signal_price_on_signals_details.setText(signalsMoreArticle.getSignal().getBuy_price().toString());
                }
                signal_current_price_on_signals_details.setText(signalsMoreArticle.getSignal().getCurrent_price().toString()+"%");

                sector_title_signals_details.setText(signalsMoreArticle.getSignal().getSector_title());




            }

            @Override
            public void onFailure(Call<SignalsMoreArticle> call, Throwable t) {

            }
        });

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
