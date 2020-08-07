package com.example.cityfalcon;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;

public class SignalsArticle {

   Boolean success;
   ArrayList <SignalsBuySellArticle> sell;
   ArrayList <SignalsBuySellArticle> buy;

    public boolean isSuccess() {
        return success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<SignalsBuySellArticle> getSell() {
        return sell;
    }

    public void setSell(ArrayList<SignalsBuySellArticle> sell) {
        this.sell = sell;
    }

    public ArrayList<SignalsBuySellArticle> getBuy() {
        return buy;
    }

    public void setBuy(ArrayList<SignalsBuySellArticle> buy) {
        this.buy = buy;
    }
}
