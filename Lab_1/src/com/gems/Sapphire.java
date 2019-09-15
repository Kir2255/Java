package com.gems;

import com.gemstone.Gemstone;

public class Sapphire extends Gemstone {
    public String getGemName(){
        return gemName;
    }

    public void setGemName(String value){
        this.gemName = value;
    }

    public float getCarat(){
        return carat;
    }

    public void setCarat(float value){
        carat = value;
    }

    public float getGemCost(){
        return gemCost;
    }

    public void setGemCost(float value){
        gemCost = value;
    }

    public float getGemTransperancy(){
        return gemTransperancy;
    }

    public void setGemTransperancy(float value){
        gemTransperancy = value;
    }

    public Sapphire(float carat, float gemCost, float gemTransperncy){
        this.gemName = "Сапфир";
        this.gemTransperancy = gemTransperncy;
        this.gemCost = gemCost;
        this.carat = carat;
    }
}
