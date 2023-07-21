package com.amithgc.billgenerator.pojo;

public class PDFFIllData {
    private String text;
    private int locationX;
    private int locationY;
    private int fontSize;
    private String color;
    private String font;
    private String enricher;

    public PDFFIllData() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLocationX() {
        return locationX;
    }

    public void setLocationX(int locationX) {
        this.locationX = locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public void setLocationY(int locationY) {
        this.locationY = locationY;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

    public String getEnricher() {
        return enricher;
    }

    public void setEnricher(String enricher) {
        this.enricher = enricher;
    }
}
