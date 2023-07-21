package com.amithgc.billgenerator.enricher;

import com.amithgc.billgenerator.enricher.utils.Enricher;

import java.text.SimpleDateFormat;
import java.util.Date;

@Enricher
public class CurrentTimeEnricher implements IEnricher {
    @Override
    public String getName() {
        return "TimeNow";
    }

    @Override
    public String process() {
        return getDate();
    }

    private String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a");
        return dateFormat.format(date);
    }
}
