package com.gems;

import com.gemstone.Gemstone;

public class Topaz extends Gemstone {
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

    public Topaz(float carat, float gemCost, float gemTransperncy){
        this.gemName = "Топаз";
        this.gemTransperancy = gemTransperncy;
        this.gemCost = gemCost;
        this.carat = carat;
    }
}
