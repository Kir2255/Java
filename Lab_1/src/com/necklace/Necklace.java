package com.necklace;

import com.gemstone.Gemstone;

import java.util.ArrayList;

public class Necklace {
    private ArrayList<Gemstone> gems;

    public ArrayList<Gemstone> GetGems(){
        return gems;
    }


    public Necklace(ArrayList<Gemstone> gems){
        this.gems = gems;
    }

    public Double TotalWeight(){
        double totalWeight = 0;
        for (Gemstone gemstone : gems){
            totalWeight += gemstone.carat;
        }

        return totalWeight;
    }

    public Double TotalCost(){
        double totalCost = 0;
        for (Gemstone gemstone : gems){
            totalCost += gemstone.gemCost;
        }

        return totalCost;
    }

    public void CostSorting(){
        for (int i = 0; i < gems.size(); i++){
            double min = gems.get(0).gemCost;
            int index = 0;

            for (int j = i + 1; j < gems.size(); j++){
                if (min > gems.get(i).gemCost){
                    min = gems.get(i).gemCost;
                    index = j;
                }
            }

            if (i != index){
                Gemstone tempGem = gems.get(i);
                gems.set(i, gems.get(index));
                gems.set(index, tempGem);
            }
        }
    }

    public ArrayList<Gemstone> TransperancyRange(double left, double right){
        ArrayList<Gemstone> temp = new ArrayList<>();

        for (Gemstone gem : gems){
            if (gem.gemTransperancy >= left && gem.gemTransperancy <= right){
                temp.add(gem);
            }
        }

        return temp;
    }
}
