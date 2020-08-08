package com.tradestock.cityfalcom.Models;

import java.util.ArrayList;

public class FiltersArticle {

    private Boolean success;
    private ArrayList<InstrumentsForFilters> instruments;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ArrayList<InstrumentsForFilters> getInstruments() {
        return instruments;
    }

    public void setInstruments(ArrayList<InstrumentsForFilters> instruments) {
        this.instruments = instruments;
    }
}
