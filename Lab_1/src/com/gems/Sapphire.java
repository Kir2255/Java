package com.gems;

import com.gemstone.Gemstone;

public class Sapphire extends Gemstone {
    public String getGemName(){
        return gemName;
    }

    public float getCarat(){
        return carat;
    }

    public float getGemCost(){
        return gemCost;
    }

    public float getGemTransperancy(){
        return gemTransperancy;
    }

    public Sapphire(float carat, float gemCost, float gemTransperncy){
        this.gemName = "Сапфир";
        this.gemTransperancy = gemTransperncy;
        this.gemCost = gemCost;
        this.carat = carat;
    }
}
