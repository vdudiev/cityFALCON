package com.tradestock.cityfalcom.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.tradestock.cityfalcom.Models.InstrumentArticle;
import com.tradestock.cityfalcom.Models.Instruments;
import com.tradestock.cityfalcom.Models.Lang;
import com.tradestock.cityfalcom.Models.Languages;
import com.tradestock.cityfalcom.Networking.RegistrationResponse;
import com.tradestock.cityfalcom.Networking.RetrofitCreate;
import com.tradestock.cityfalcom.R;
import com.tradestock.cityfalcom.TradeApp;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreenActivity extends AppCompatActivity {

    private static  int SPLASH_TIME_OUT = 1000;

    Locale getCurrentLocale(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            return getResources().getConfiguration().getLocales().get(0);
        } else{
            return getResources().getConfiguration().locale;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        RetrofitCreate.getRetrofit().getLanguages(RegistrationResponse.getAccept(),RegistrationResponse.getAuthorization()).enqueue(new Callback<Languages>() {
            @Override
            public void onResponse(Call<Languages> call, Response<Languages> response) {
                TradeApp.langs = response.body().getLang().get(0);
                String s = getCurrentLocale().getLanguage();
                for (Lang langs : response.body().getLang()){
                    if (langs.getTag().equals(s)){
                        RegistrationResponse.setLang(s);
                        TradeApp.langs = langs;
                    }
                }
                RetrofitCreate.getRetrofit().GetInstruments(RegistrationResponse.getAccept(),RegistrationResponse.getAuthorization())
                        .enqueue(new Callback<Instruments>() {
                            @Override
                            public void onResponse(Call<Instruments> call, Response<Instruments> response) {
                                TradeApp.instruments = response.body().getInstruments();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent homeIntent = new Intent( SplashScreenActivity.this, HomeActivity.class);
                                        startActivity(homeIntent);
                                        finish();
                                    }
                                }, SPLASH_TIME_OUT);
                            }

                            @Override
                            public void onFailure(Call<Instruments> call, Throwable t) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent homeIntent = new Intent( SplashScreenActivity.this, HomeActivity.class);
                                        startActivity(homeIntent);
                                        finish();
                                    }
                                }, SPLASH_TIME_OUT);
                            }
                        });
            }

            @Override
            public void onFailure(Call<Languages> call, Throwable t) {
                Toast.makeText(SplashScreenActivity.this,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                finish();
            }
        });



    }
}
