package com.amithgc.billgenerator;

import com.amithgc.billgenerator.pojo.PDFFIllData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.List;

public class JsonReader {

    public List<PDFFIllData> read(String fileName) {

        try {
            // Read the JSON file into a StringBuilder
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
            br.close();

            // Convert the JSON content to a list of Java objects
            Type listType = new TypeToken<List<PDFFIllData>>() {
            }.getType();

            return new Gson().fromJson(jsonContent.toString(), listType);
        } catch (Exception e) {
            System.out.println("Sorry, We are not able to open '" + fileName + "' Make sure the file exists.");
        }
        return null;
    }
}
