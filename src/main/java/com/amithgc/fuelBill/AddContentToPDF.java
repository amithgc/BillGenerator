package com.amithgc.fuelBill;

import com.amithgc.Utils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AddContentToPDF {

    public static final double DEFAULT_FUEL_PRICE = 100;
    private static final String NAME = "AMITH GC";
    private static final String VEHICLE = "KA03MR1222";
    private static final double TOTAL_AMOUNT = 16000;
    private static final double MAX_BILL_AMOUNT = 5000;

    public static void main(String[] args) throws IOException, DocumentException {

        AddContentToPDF obj = new AddContentToPDF();
        List<FuelData> fuelBillData = obj.generateData();

        for (FuelData fuelBillDatum : fuelBillData) {
            obj.generatePDF(fuelBillDatum);
        }
    }

    private static void generateText(String text, int x, int y, PdfContentByte over, BaseFont font) {
        over.beginText();
        over.setColorFill(new BaseColor(72, 74, 79));
        over.setFontAndSize(font, 5);
        over.setTextMatrix(x, y);
        over.showText(text);
        over.endText();
    }

    private void generatePDF(FuelData data) {
        try {
            PdfReader reader = new PdfReader("basePDF/Fuel-Bill.pdf");
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("output/Fuel-Bill-" + data.getDate().replace(" ", "-") + ".pdf"));

            BaseFont font = BaseFont.createFont("fonts/PressStart2P-Regular.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
            PdfContentByte over = stamper.getOverContent(1);

            generateText(NAME, 85, 143, over, font);
            generateText(VEHICLE, 55, 150, over, font);
            generateText(String.valueOf(data.getId()), 72, 218, over, font);
            generateText(String.valueOf(data.getPrice()), 70, 188, over, font);
            generateText(String.valueOf(data.getAmount()), 60, 181, over, font);
            generateText(Utils.formatDouble(data.getQuantity()) + " lt", 79, 174, over, font);
            generateText(data.getDate(), 11, 109, over, font);
            generateText(data.getTime(), 76, 110, over, font);

            stamper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<FuelData> generateData() {

        int id = 1;
        double price = 101.11;

        List<FuelData> dataList = new ArrayList<>();
        double remainder = TOTAL_AMOUNT % MAX_BILL_AMOUNT;
        int count = (int) (TOTAL_AMOUNT / MAX_BILL_AMOUNT);

        Date begining, end;

        {
            Calendar calendar = Utils.getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
            Utils.setTimeToBeginningOfDay(calendar);
            begining = calendar.getTime();
        }

        {
            Calendar calendar = Utils.getCalendarForNow();
            calendar.set(Calendar.DAY_OF_MONTH,
                    calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            Utils.setTimeToEndofDay(calendar);
            end = calendar.getTime();
        }

        long beginTime = begining.getTime();
        long endTime = end.getTime();
        long difference = endTime - beginTime;

        long duration = difference / (count + 3);
        long currentTime = beginTime;

        for (int i = 0; i < count; i++) {
            currentTime = currentTime + duration;

            id = (int) (id + (Math.random() * 1100));
            FuelData fuelDataObj = new FuelData();
            fuelDataObj.setAmount(MAX_BILL_AMOUNT);
            fuelDataObj.setId(id);

            Date date = new Date(currentTime);
            fuelDataObj.setDate(Utils.dateToString(date, "dd MMM yyyy"));
            fuelDataObj.setTime(Utils.getRandomTime());

            price = FuelPrices.getFuelPrice(Utils.dateToString(date, "yyyy MM dd"));
            fuelDataObj.setPrice(price);
            fuelDataObj.setQuantity(MAX_BILL_AMOUNT / price);

            dataList.add(fuelDataObj);
        }

        id = (int) (id + (Math.random() * 1200));
        currentTime = currentTime + duration;

        FuelData fuelDataObj = new FuelData();
        fuelDataObj.setAmount(remainder);
        fuelDataObj.setId(id);

        Date date = new Date(currentTime);
        fuelDataObj.setDate(Utils.dateToString(date, "dd MMM yyyy"));
        fuelDataObj.setTime(Utils.getRandomTime());

        price = FuelPrices.getFuelPrice(Utils.dateToString(date, "yyyy MM dd"));
        fuelDataObj.setPrice(price);
        fuelDataObj.setQuantity(remainder / price);

        dataList.add(fuelDataObj);

        return dataList;
    }
}
