package com.example.cityfalcon;

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
    private TextView signals_details_symbol_mid;
    private TextView signal_date_time_on_signals_details;
    private TextView signal_price_on_signals_details;
    private TextView signal_current_price_on_signals_details;
    private TextView sector_title_signals_details;


    private RegistrationResponse registrationResponse;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_signal_details, container, false);
        Button buttonBackToSignals = root.findViewById(R.id.bottom_back_to_signals_from_details);

        //TextView signals_details_symbol_up = root.findViewById(R.id.textView_signals_details_symbol_up);

       // registrationResponse = new RegistrationResponse();
        //Retrofit retrofit = retrofitCreate.getRetrofit();
        //ApiService apiService = retrofit.create(ApiService.class);
        //Float signal_id = (float) 7;
        //String lang = "en";
        /*apiService.GetMoreAboutSignal(registrationResponse.getAccept(),registrationResponse.getAuthorization(), signal_id, lang).enqueue(new Callback<SignalsMoreArticle>() {
            @Override
            public void onResponse(Call<SignalsMoreArticle> call, Response<SignalsMoreArticle> response) {
                assert response.body() != null;
                response.body().getSignal();
            }

            @Override
            public void onFailure(Call<SignalsMoreArticle> call, Throwable t) {

            }
        });*/

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
