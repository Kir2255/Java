package com.gems;

import com.gemstone.Gemstone;

public class Garnet extends Gemstone {
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

    public Garnet( float carat, float gemCost, float gemTransperncy){
        this.gemName = "Гранат";
        this.gemTransperancy = gemTransperncy;
        this.gemCost = gemCost;
        this.carat = carat;
    }
}
