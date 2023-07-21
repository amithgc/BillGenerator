package com.amithgc.billgenerator;

import com.amithgc.billgenerator.pojo.PDFFIllData;

import java.util.List;

public class App {
    public static void main(String[] args) {
        if (args.length != 6) {
            System.out.println("Usage: Please provide the options as follows:");
            System.out.println("-t <template_name> -j <json_input_file> -o <output_pdf_file>");
            System.out.println("Example java -jar App.jar -t ./data/base-pdf/fuel-bill-sample-1.pdf -j ./data/json/fuel-bill-sample-1.json -o ./output/out.pdf");
            System.exit(0);
        }

        String templateName = null;
        String jsonInputFile = null;
        String outputPdfFile = null;

        // Parse command-line arguments
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-t")) {
                templateName = args[i + 1];
            } else if (args[i].equals("-j")) {
                jsonInputFile = args[i + 1];
            } else if (args[i].equals("-o")) {
                outputPdfFile = args[i + 1];
            }
        }

        if (templateName == null || jsonInputFile == null || outputPdfFile == null) {
            System.out.println("Usage: Please provide all the required options.");
            System.exit(0);
        }

        JsonReader reader = new JsonReader();
        List<PDFFIllData> data = reader.read(jsonInputFile);

        if (data != null) {
            PDFGenerator generator = new PDFGenerator();
            generator.generate(data, templateName, outputPdfFile);
        }
    }
}





