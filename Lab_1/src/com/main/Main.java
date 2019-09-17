package com.main;

import com.gems.*;
import com.gemstone.Gemstone;
import com.necklace.Necklace;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Gemstone> gemstones = new ArrayList<>();  // Массив драгоценных камней
        ArrayList<Double> temp = new ArrayList<>(3);

        boolean flag = true;
        int choice = 0;
	    do {
	        System.out.println("\t\t\t\t\tМеню");
            System.out.println("1. Отобрать камни для ожерелья");
            System.out.println("2. Показать камни для ожерелья");
            System.out.println("3. Посчитать общий вес камней и их стоимость");
            System.out.println("4. Отсортировать камни ожерелья по ценности");
            System.out.println("5. Поиск камней ожерелья по заданному диапазону прозрачности");
            System.out.println("0. Выход");


            try {
                System.out.print("\nПункт меню:\t");
                choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1:
                        AddGem(gemstones, temp);
                        break;
                    case 2:
                        GemOutput(new Necklace(gemstones).GetGems());
                        break;
                    case 3:
                        GemOutput(new Necklace(gemstones).GetGems());

                        double weight = new Necklace(gemstones).TotalWeight();
                        double cost = new Necklace(gemstones).TotalCost();

                        System.out.println("Общий вес камней :\t" + weight + "\t Общая стоимость : \t" + cost);
                        break;
                    case 4:
                        //GemOutput(new Necklace(gemstones).GetGems());

                        Necklace necklace = new Necklace(gemstones);
                        necklace.CostSorting();

                        GemOutput(necklace.GetGems());
                        break;
                    case 5:
                        boolean newFlag = true;

                        do {
                            try {
                                System.out.print("Введите левую границу диапазона прозрачности :\t");
                                double left = new Scanner(System.in).nextDouble();

                                System.out.print("Введите правую границу диапазона прозрачности :\t");
                                double right = new Scanner(System.in).nextDouble();

                                if (left < right) {
                                    necklace = new Necklace(gemstones);

                                    GemOutput(necklace.TransperancyRange(left, right));

                                    newFlag = false;
                                } else {
                                    System.out.println("Ошибка!!! Повторите ввод.");
                                    continue;
                                }
                            } catch (Exception e) {
                                System.out.println("Ошибка!!! Повторите ввод.");
                            }
                        } while (newFlag);

                        break;
                    case 0:
                        flag = false;
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Ошибка!!! Повторите ввод.");
            }
        }while (flag);


    }

    private static void AddGem(ArrayList<Gemstone> gemstones, ArrayList<Double> temp){
        boolean localFlag = true;
        int choice = 0;
        do {
            System.out.println("\t\t\tДобавить камни");
            System.out.println("1. Алмаз");
            System.out.println("2. Топаз");
            System.out.println("3. Гранат");
            System.out.println("4. Сапфир");
            System.out.println("5. Циркон");
            System.out.println("6. Турмалин");
            System.out.println("0. Назад");

            try {
                System.out.print("\nПункт меню:\t");
                choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1:
                        InputStats(temp);
                        gemstones.add(new Diamond(temp.get(0).floatValue(), temp.get(1).floatValue(), temp.get(2).floatValue()));
                        temp.clear();
                        break;
                    case 2:
                        InputStats(temp);
                        gemstones.add(new Topaz(temp.get(0).floatValue(), temp.get(1).floatValue(), temp.get(2).floatValue()));
                        temp.clear();
                        break;
                    case 3:
                        InputStats(temp);
                        gemstones.add(new Garnet(temp.get(0).floatValue(), temp.get(1).floatValue(), temp.get(2).floatValue()));
                        temp.clear();
                        break;
                    case 4:
                        InputStats(temp);
                        gemstones.add(new Sapphire(temp.get(0).floatValue(), temp.get(1).floatValue(), temp.get(2).floatValue()));
                        temp.clear();
                        break;
                    case 5:
                        InputStats(temp);
                        gemstones.add(new Zircon(temp.get(0).floatValue(), temp.get(1).floatValue(), temp.get(2).floatValue()));
                        temp.clear();
                        break;
                    case 6:
                        InputStats(temp);
                        gemstones.add(new Tourmaline(temp.get(0).floatValue(), temp.get(1).floatValue(), temp.get(2).floatValue()));
                        temp.clear();
                        break;
                    case 0:
                        localFlag = false;
                        break;
                    default:
                        localFlag = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ошибка!!! Повторите ввод.");
            }
        }while (localFlag);
    }

    private static void InputStats(ArrayList<Double> temp){
        boolean flag = true;
        do {
            try{
                System.out.print("Введите вес камня в каратах :\t");
                temp.add(new Scanner(System.in).nextDouble());
                flag = false;
            }catch (Exception e){
                System.out.println("Ошибка!!! Повторите ввод.");
            }
        }while (flag);

        flag = true;
        do {
            try{
                System.out.print("Введите стоимость камня :\t");
                temp.add(new Scanner(System.in).nextDouble());
                flag = false;
            }catch (Exception e){
                System.out.println("Ошибка!!! Повторите ввод.");
            }
        }while (flag);

        flag = true;
        do {
            try{
                System.out.print("Введите прозрачность камня :\t");
                temp.add(new Scanner(System.in).nextDouble());
                flag = false;
            }catch (Exception e){
                System.out.println("Ошибка!!! Повторите ввод.");
            }
        }while (flag);
    }

    private static void GemOutput(ArrayList<Gemstone> gemstones){
        System.out.println("\t\t\tСписок камней");

        for (Gemstone gem : gemstones){
            System.out.println("Тип камня : " + gem.gemName + " Вес камня : " + gem.carat + " карат. Стоимость : " +
                    gem.gemCost + " Прозрачность : " + gem.gemTransperancy);
        }
    }
}
