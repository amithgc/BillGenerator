package com.amithgc.actbill;

import com.amithgc.Utils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ActBill {

    public static final int GENERATION_MONTHS = 1;

    public static void main(String[] args) {

        ActBill obj = new ActBill();
        List<ActData> actBillData = obj.generateData();

        for (ActData actBillDatum : actBillData) {
            obj.generatePDF(actBillDatum);
        }
    }

    private static void generateText(String text, int x, int y, PdfContentByte over, BaseFont font, int fontSize) {
        over.beginText();
        over.setColorFill(new BaseColor(119, 120, 123));
        over.setFontAndSize(font, fontSize);
        over.setTextMatrix(x, y);
        over.showText(text);
        over.endText();
    }

    private List<ActData> generateData() {
        List<ActData> data = new ArrayList<>();
        for (int i = 0; i < GENERATION_MONTHS; i++) {
            ActData dt = new ActData();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.MONTH, -i);
            calendar.set(Calendar.DATE, 1);
            dt.setDate(calendar.getTime());


            dt.setFileName("ACT-Invoice-" + Utils.formatDate(dt.getDate(), "MMM-yyyy") + ".pdf");
            dt.setBillingPeriod(Utils.formatDate(dt.getDate(), "MMM, yyyy"));
            dt.setInvoiceDate(Utils.formatDate(dt.getDate(), "dd/MM/yyyy"));
            dt.setDueDate(Utils.addDays(dt.getDate(), 15, "dd/MM/yyyy"));
            dt.setInvoiceNumber("KA-B1-" + Utils.subtractDays(dt.getDate(), 9000 + (i * 99), "yyyyMMdd"));
            data.add(dt);
        }

        return data;
    }

    private void generatePDF(ActData data) {
        try {
            PdfReader reader = new PdfReader("basePDF/ACT.pdf");
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("output/" + data.getFileName()));

            BaseFont font = BaseFont.createFont("fonts/ClearSans-Bold.ttf", BaseFont.CP1252, BaseFont.EMBEDDED);
            PdfContentByte over = stamper.getOverContent(1);

            generateText(data.getInvoiceNumber(), 360, 760, over, font, 31);
            generateText(data.getBillingPeriod(), 260, 560, over, font, 45);
            generateText(data.getInvoiceDate(), 640, 560, over, font, 45);
            generateText(data.getDueDate(), 1500, 560, over, font, 45);

            stamper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
