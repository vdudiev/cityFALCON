package com.example.cityfalcon.Models;

import com.example.cityfalcon.Models.OriginForSignals;
import com.example.cityfalcon.Models.SectorForSignals;

public class SignalFromSignalsBuySellArticle {

    private Integer id;
    private String symbol;
    private Integer origin_id;
    private String order;
    private String date_time;
    private Integer instrument_id;
    private Integer sector_id;
    private Float price;
    private Float current_price;
    private Float take_profit;
    private Float stop_loss;
    private String detailed_signal_info;
    private Float paid_subscription;
    private String created_at;
    private String updated_at;
    private Integer watchlist;
    private OriginForSignals origin;
    private SectorForSignals sector;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getOrigin_id() {
        return origin_id;
    }

    public void setOrigin_id(Integer origin_id) {
        this.origin_id = origin_id;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public Integer getInstrument_id() {
        return instrument_id;
    }

    public void setInstrument_id(Integer instrument_id) {
        this.instrument_id = instrument_id;
    }

    public Integer getSector_id() {
        return sector_id;
    }

    public void setSector_id(Integer sector_id) {
        this.sector_id = sector_id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Float current_price) {
        this.current_price = current_price;
    }

    public Float getTake_profit() {
        return take_profit;
    }

    public void setTake_profit(Float take_profit) {
        this.take_profit = take_profit;
    }

    public Float getStop_loss() {
        return stop_loss;
    }

    public void setStop_loss(Float stop_loss) {
        this.stop_loss = stop_loss;
    }

    public String getDetailed_signal_info() {
        return detailed_signal_info;
    }

    public void setDetailed_signal_info(String detailed_signal_info) {
        this.detailed_signal_info = detailed_signal_info;
    }

    public Float getPaid_subscription() {
        return paid_subscription;
    }

    public void setPaid_subscription(Float paid_subscription) {
        this.paid_subscription = paid_subscription;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }


    public Integer getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(Integer watchlist) {
        this.watchlist = watchlist;
    }

    public OriginForSignals getOrigin() {
        return origin;
    }

    public void setOrigin(OriginForSignals origin) {
        this.origin = origin;
    }

    public SectorForSignals getSector() {
        return sector;
    }

    public void setSector(SectorForSignals sector) {
        this.sector = sector;
    }
}
