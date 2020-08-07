package com.example.cityfalcon.Models;

public class OneSignalMoreArticle {
    private Float Signal_id;
    private String symbol;
    private String date_time;
    private Float sector_id;
    private String sector_title;
    private Float sell_price;
    private Float buy_price;
    private Float current_price;

    public Float getSignal_id() {
        return Signal_id;
    }

    public void setSignal_id(Float signal_id) {
        Signal_id = signal_id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Float getSector_id() {
        return sector_id;
    }

    public void setSector_id(Float sector_id) {
        this.sector_id = sector_id;
    }

    public String getSector_title() {
        return sector_title;
    }

    public void setSector_title(String sector_title) {
        this.sector_title = sector_title;
    }

    public Float getSell_price() {
        return sell_price;
    }

    public void setSell_price(Float sell_price) {
        this.sell_price = sell_price;
    }

    public Float getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(Float buy_price) {
        this.buy_price = buy_price;
    }

    public Float getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Float current_price) {
        this.current_price = current_price;
    }
}
