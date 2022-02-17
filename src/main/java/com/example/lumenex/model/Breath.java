package com.example.lumenex.model;

import com.squareup.moshi.Json;

import java.util.Date;
import java.util.List;

@Json
public class Breath {

    private Date date;
    private boolean isValid;
    private Double[] flow;

    public Breath(Date date, boolean isValid, Double[] flow) {
        this.date = date;
        this.isValid = isValid;
        this.flow = flow;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date Date) {
        date = Date;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        isValid = isValid;
    }

    public Double[] getFlow() {
        return flow;
    }

    public void setFlow(Double[] flow) {
        flow = flow;
    }
}
