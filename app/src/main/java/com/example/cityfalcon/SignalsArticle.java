package com.example.cityfalcon;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;

public class SignalsArticle {

   Boolean success;
   SignalsBuySellArticle sell;
   SignalsBuySellArticle buy;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public SignalsBuySellArticle getSell() {
        return sell;
    }

    public void setSell(SignalsBuySellArticle sell) {
        this.sell = sell;
    }

    public SignalsBuySellArticle getBuy() {
        return buy;
    }

    public void setBuy(SignalsBuySellArticle buy) {
        this.buy = buy;
    }
}
