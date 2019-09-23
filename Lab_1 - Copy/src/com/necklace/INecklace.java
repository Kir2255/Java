package com.necklace;

import com.gemstone.Gemstone;

import java.util.ArrayList;

public interface INecklace {
    Double TotalWeight();
    Double TotalCost();
    String getType();
    void Sorting();
    ArrayList<Gemstone> getGems();
    ArrayList<Gemstone> TransperancyRange(double left, double right);



}
