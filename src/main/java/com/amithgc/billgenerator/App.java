package com.amithgc.billgenerator;

import com.amithgc.billgenerator.pojo.PDFFIllData;

import java.util.List;

public class App {
    public static void main(String[] args) {

        JsonReader reader = new JsonReader();
        List<PDFFIllData> data = reader.read("bill-sample-1.json");

        PDFGenerator generator = new PDFGenerator();
        generator.generate(data, "bill-sample-1.pdf", "o1.pdf");
    }
}
