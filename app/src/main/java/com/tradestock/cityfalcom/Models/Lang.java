package com.tradestock.cityfalcom.Models;

import java.util.Date;

public class Lang {
    private long id;
    private String tag;
    private String name;
    private String terms_conditions;
    private String subscription;
    private Object created_at;
    private Date updated_at;

    public long getID() { return id; }
    public void setID(long value) { this.id = value; }

    public String getTag() { return tag; }
    public void setTag(String value) { this.tag = value; }

    public String getName() { return name; }
    public void setName(String value) { this.name = value; }

    public String getTermsConditions() {
        return terms_conditions;
    }
    public void setTermsConditions(String value) { this.terms_conditions = value; }

    public String getSubscription() { return subscription; }
    public void setSubscription(String value) { this.subscription = value; }

    public Object getCreatedAt() { return created_at; }
    public void setCreatedAt(Object value) { this.created_at = value; }

    public Date getUpdatedAt() { return updated_at; }
    public void setUpdatedAt(Date value) { this.updated_at = value; }
}
