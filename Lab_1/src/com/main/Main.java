package com.main;

import com.gems.*;
import com.gemstone.Gemstone;

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
	        System.out.println("1. Добавить камни");
            System.out.println("2. Отобрать камни для ожерелья");
            System.out.println("3. Посчитать общий вес камней и их стоимость");
            System.out.println("4. Отсортировать камни ожерелья по ценности");
            System.out.println("5. Поиск камней ожерелья по заданному диапазону прозрачности");
            System.out.println("0. Выхлд");


            try {
                System.out.print("\nПункт меню:\t");
                choice = new Scanner(System.in).nextInt();

                switch (choice){
                    case 1:
                        AddGem(gemstones, temp);
                        for(Gemstone gemstone:gemstones){
                            System.out.println(gemstone.gemName);
                        }
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 0:
                        flag = false;
                        break;
                }
            }
            catch (Exception e){
                System.out.println("Ошибка!!! Повторите ввод.");
            }
        }while (flag && (choice < 0 || choice > 5));
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

                }
            } catch (Exception e) {
                System.out.println("Ошибка!!! Повторите ввод.");
            }
        }while (localFlag && (choice < 0 || choice > 6));
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
}
