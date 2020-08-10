package com.tradestock.cityfalcom.Models;


import java.util.List;


public class Instruments {
    private boolean success;
    private List<InstrumentsForFilters> instruments;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public List<InstrumentsForFilters> getInstruments() { return instruments; }
    public void setInstruments(List<InstrumentsForFilters> value) { this.instruments = value; }
}

// Instrument.java




