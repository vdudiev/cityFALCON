package com.tradestock.cityfalcom.Models;

import java.util.List;

public class Languages {
    private boolean success;
    private List<Lang> lang;

    public boolean getSuccess() { return success; }
    public void setSuccess(boolean value) { this.success = value; }

    public List<Lang> getLang() { return lang; }
    public void setLang(List<Lang> value) { this.lang = value; }
}
