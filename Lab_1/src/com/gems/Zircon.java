package com.gems;

import com.gemstone.Gemstone;

public class Zircon extends Gemstone {
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

    public Zircon(float carat, float gemCost, float gemTransperncy){
        this.gemName = "Циркон";
        this.gemTransperancy = gemTransperncy;
        this.gemCost = gemCost;
        this.carat = carat;
    }
}
