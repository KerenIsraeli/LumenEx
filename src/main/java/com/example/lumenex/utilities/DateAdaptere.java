package com.example.lumenex.utilities;

import com.squareup.moshi.FromJson;
import com.squareup.moshi.ToJson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdaptere {

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");

    @ToJson
    public String toJson(Date date) {
        return date.toString();
    }

    @FromJson
    public Date fromJson(String dateString) {
        try {
            return formatter.parse(dateString);
        }
        catch (ParseException e) {
            return new Date();
        }

    }
}
