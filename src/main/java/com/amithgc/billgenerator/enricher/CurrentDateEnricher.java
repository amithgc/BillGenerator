package com.amithgc.billgenerator.enricher;

import com.amithgc.billgenerator.enricher.utils.Enricher;

import java.text.SimpleDateFormat;
import java.util.Date;

@Enricher
public class CurrentDateEnricher implements IEnricher {
    @Override
    public String getName() {
        return "DateToday";
    }

    @Override
    public String process() {
        return getDate();
    }

    private String getDate() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(date);
    }
}
