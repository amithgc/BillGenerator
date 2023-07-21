package com.amithgc.billgenerator;

import com.amithgc.billgenerator.enricher.IEnricher;
import com.amithgc.billgenerator.enricher.utils.EnricherFinder;
import com.amithgc.billgenerator.pojo.PDFFIllData;
import com.amithgc.billgenerator.utils.CommonUtils;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.lang.reflect.Constructor;
import java.util.List;

public class PDFGenerator {

    public boolean generate(List<PDFFIllData> data, String basePDFName, String outputFileName) {

        try {
            List<Class<?>> enrichers = EnricherFinder.getAllEnrichers();

            PdfReader reader = new PdfReader(basePDFName);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(outputFileName));


            PdfContentByte over = stamper.getOverContent(1);

            String text;
            for (PDFFIllData datum : data) {
                text = datum.getText();

                if (datum.getEnricher() != null) {
                    try {
                        for (Class<?> enricher : enrichers) {
                            Constructor<?> constructor = enricher.getDeclaredConstructor();
                            IEnricher instance = (IEnricher) constructor.newInstance();
                            if (instance.getName().equalsIgnoreCase(datum.getEnricher())) {
                                text = instance.process();
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


                BaseFont font = BaseFont.createFont("fonts/" + datum.getFont(), BaseFont.CP1252, BaseFont.EMBEDDED);
                generateText(text, datum.getLocationX(), datum.getLocationY(), over, font, datum.getFontSize(), datum.getColor());

            }


            stamper.close();

        } catch (Exception e) {
            System.out.println("Sorry, We are not able to open '" + basePDFName + "' Make sure the file exists.");
        }
        return false;
    }

    private void generateText(String text, int x, int y, PdfContentByte over, BaseFont font, int fontSize, String colorHex) {

        List<Integer> color = CommonUtils.hexToRGB(colorHex);

        over.beginText();
        over.setColorFill(new BaseColor(color.get(0), color.get(1), color.get(2)));
        over.setFontAndSize(font, fontSize);
        over.setTextMatrix(x, y);
        over.showText(text);
        over.endText();
    }


}
