package com.amithgc.billgenerator.utils;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    public static List<Integer> hexToRGB(String hexColor) {
        if (hexColor.startsWith("#")) {
            hexColor = hexColor.substring(1);
        }

        // Parse the hexadecimal string to integer values for each color component
        int red = Integer.parseInt(hexColor.substring(0, 2), 16);
        int green = Integer.parseInt(hexColor.substring(2, 4), 16);
        int blue = Integer.parseInt(hexColor.substring(4, 6), 16);

        List<Integer> rgbColors = new ArrayList<>();
        rgbColors.add(red);
        rgbColors.add(green);
        rgbColors.add(blue);

        return rgbColors;
    }
}
