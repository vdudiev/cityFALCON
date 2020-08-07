package com.example.cityfalcon.Models;

import com.example.cityfalcon.Models.SignalsBuySellArticle;

import java.util.ArrayList;

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
