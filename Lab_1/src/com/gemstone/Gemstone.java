package com.gemstone;

public abstract class Gemstone {
    protected String gemName = null;          // Намиенование
    protected float carat = 0f;               // Вес камня в каратах
    protected float gemCost = 0f;             // Стоимость
    protected float gemTransperancy = 0f;    // Прозрачность камня

    public String getGemName() {
        return gemName;
    }

    public float getCarat() {
        return carat;
    }

    public float getGemCost() {
        return gemCost;
    }

    public float getGemTransperancy() {
        return gemTransperancy;
    }
}
