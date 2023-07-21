package com.amithgc.billgenerator.enricher;

import com.amithgc.billgenerator.enricher.utils.Enricher;

import java.util.Random;

@Enricher
public class RandomNumberEnricher implements IEnricher {
    @Override
    public String getName() {
        return "RandomNumber";
    }

    @Override
    public String process() {
        return String.valueOf(generateRandomNumber(10));
    }

    private long generateRandomNumber(int numDigits) {
        if (numDigits <= 0 || numDigits > 18) {
            throw new IllegalArgumentException("Number of digits must be between 1 and 18 (inclusive).");
        }

        Random random = new Random();
        long lowerBound = (long) Math.pow(10, numDigits - 1);
        long upperBound = (long) Math.pow(10, numDigits) - 1;

        int positiveRandomInt = random.nextInt((int) (upperBound - lowerBound + 1));
        return lowerBound + positiveRandomInt;
    }
}

