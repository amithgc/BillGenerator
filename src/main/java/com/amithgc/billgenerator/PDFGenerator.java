package com.amithgc.billgenerator;

import com.amithgc.billgenerator.pojo.PDFFIllData;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.FileOutputStream;
import java.util.List;

public class PDFGenerator {

    public boolean generate(List<PDFFIllData> data, String basePDFName, String outputFileName) {

        try {
            PdfReader reader = new PdfReader("./data/base-pdf/" + basePDFName);
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("output/" + outputFileName));


        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
