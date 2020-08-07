package com.example.cityfalcon;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Date;

public class SignalsArticle {

  private Boolean success;
  private ArrayList<SignalFromSignalsBuySellArticle> sell;
  private ArrayList<SignalFromSignalsBuySellArticle> buy;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<SignalFromSignalsBuySellArticle> getSell() {
        return sell;
    }

    public void setSell(ArrayList<SignalFromSignalsBuySellArticle> sell) {
        this.sell = sell;
    }

    public ArrayList<SignalFromSignalsBuySellArticle> getBuy() {
        return buy;
    }

    public void setBuy(ArrayList<SignalFromSignalsBuySellArticle> buy) {
        this.buy = buy;
    }
}
