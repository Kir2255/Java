package com.gemstone;

public class Gemstone {
    protected String gemName = null;          // Намиенование
    protected double carat = 0f;               // Вес камня в каратах
    protected double gemCost = 0f;             // Стоимость
    protected double gemTransperancy = 0f;    // Прозрачность камня

    public String getGemName() {
        return gemName;
    }

    public double getCarat() {
        return carat;
    }

    public double getGemCost() {
        return gemCost;
    }

    public double getGemTransperancy() {
        return gemTransperancy;
    }

    public Gemstone(String gemName, Double carat, Double gemCost, Double gemTransperancy) {
        this.gemName = gemName;
        this.carat = carat;
        this.gemCost = gemCost;
        this.gemTransperancy = gemTransperancy;
    }
}
