package com.tradestock.cityfalcom.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.tradestock.cityfalcom.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static  int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent( SplashScreenActivity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
