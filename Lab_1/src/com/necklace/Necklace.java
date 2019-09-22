package com.necklace;

import com.gemstone.Gemstone;

import java.util.ArrayList;
import java.util.Comparator;

public class Necklace {
    private ArrayList<Gemstone> gems;

    public ArrayList<Gemstone> GetGems(){
        return gems;
    }


    public Necklace(ArrayList<Gemstone> gems){
        this.gems = gems;
    }

    // Возвращает общий вес камней ожерелья
    public Double TotalWeight(){
        double totalWeight = 0;
        for (Gemstone gemstone : gems){
            totalWeight += gemstone.getCarat();
        }

        return totalWeight;
    }

    // Возвращает общую стоимость камней ожерелья
    public Double TotalCost(){
        double totalCost = 0;
        for (Gemstone gemstone : gems){
            totalCost += gemstone.getGemCost();
        }

        return totalCost;
    }

    // Сортировка камней по стоимости
    public void CostSorting(){
        gems.sort((o1, o2) -> (int) (o1.getGemCost() - o2.getGemCost()));

    }

    // Возвращает список камней, у которых значение прозрачности находится в определенном диапазоне
    public ArrayList<Gemstone> TransperancyRange(double left, double right){
        ArrayList<Gemstone> temp = new ArrayList<>();

        for (Gemstone gem : gems){
            if (gem.getGemTransperancy() >= left && gem.getGemTransperancy() <= right){
                temp.add(gem);
            }
        }

        return temp;
    }
}
