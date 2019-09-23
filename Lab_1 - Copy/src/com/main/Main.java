package com.main;

import com.gemstone.Gemstone;
import com.necklace.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Gemstone> gemstones = new ArrayList<>();  // Массив драгоценных камней
        ArrayList<INecklace> necklaces = new ArrayList<>();
        ArrayList<Object> temp = new ArrayList<>(4);


        boolean flag = true;  // Флаг-переменная для выхода из основного меню
        int choice = 0;
	    do {
	        System.out.println("\t\t\t\t\tМеню");
            System.out.println("1. Отобрать камни для ожерелья");
            System.out.println("2. Показать камни для ожерелья");
            System.out.println("3. Посчитать общий вес камней и их стоимость");
            System.out.println("4. Отсортировать камни ожерелья по ценности");
            System.out.println("5. Поиск камней ожерелья по заданному диапазону прозрачности");
            System.out.println("0. Выход");

            // Обработка исключений для ввода пользователя
            try {
                System.out.print("\nПункт меню:\t");
                choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1:
                        boolean flagA = true;
                        do {
                            try {
                                System.out.println("\t\tТип ожерелья");
                                System.out.println("1. Ожерелье из драгоценных камней");
                                System.out.println("2. Ожерелье из полудрагоценных камней");
                                System.out.println("0. Выход");

                                try {
                                    System.out.print("\nПункт меню:\t");
                                    choice = new Scanner(System.in).nextInt();

                                    switch (choice){
                                        case 1:
                                            AddGem(gemstones, temp);
                                            if (gemstones.size() > 0){
                                                necklaces.add(new NecklaceA(new ArrayList<>(gemstones), "Ожерелье из драгоценных камней"));
                                                gemstones.clear();
                                            }
                                            break;
                                        case 2:
                                            AddGem(gemstones, temp);
                                            if (gemstones.size() > 0){
                                                necklaces.add(new NecklaceB(new ArrayList<>(gemstones), "Ожерелье из полудрагоценных камней"));
                                                gemstones.clear();
                                            }
                                            break;
                                        default:
                                            flagA = false;
                                            break;
                                    }
                                }
                                catch (Exception e){
                                    System.out.println("Ошибка!!! Повторите ввод.");
                                }
                            }
                            catch (Exception e){
                                System.out.println("Ошибка!!! Повторите ввод.");
                            }
                        }while (flagA);

                        break;
                    case 2:
                        for (int i = 0; i < necklaces.size(); i++){
                            System.out.println("\n\n" + necklaces.get(i).getType() + "\n");

                            GemOutput(necklaces.get(i).getGems());
                        }
                        break;
                    case 3:
                        for (int i = 0; i < necklaces.size(); i++){
                            System.out.println("\n\n" + necklaces.get(i).getType() + "\n");
                            GemOutput(necklaces.get(i).getGems());
                            double weight = necklaces.get(i).TotalWeight();
                            double cost = necklaces.get(i).TotalCost();

                            System.out.println("Общий вес камней :\t" + weight + "\t Общая стоимость : \t" + cost);
                        }


                        break;
                    case 4:
                        for (int i = 0; i < necklaces.size(); i++){
                            System.out.println("\n\n" + necklaces.get(i).getType() + "\n");
                            necklaces.get(i).Sorting();
                            GemOutput(necklaces.get(i).getGems());
                        }
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
                                    for (int i = 0; i < necklaces.size(); i++) {
                                        GemOutput(necklaces.get(i).TransperancyRange(left, right));
                                        newFlag = false;
                                    }
                                } else {
                                    System.out.println("Ошибка!!! Повторите ввод.");
                                    continue;
                                }

                                newFlag = false;

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


    // Функция, добавляющая в список gemstones объект класса Gemstone
    private static void AddGem(ArrayList<Gemstone> gemstones, ArrayList<Object> temp){
        boolean localFlag = true;
        int choice = 0;
        do {
            System.out.println("\t\t\tДобавить камни");
            System.out.println("1. Добавить");
            System.out.println("0. Назад");

            try {
                System.out.print("\nПункт меню:\t");
                choice = new Scanner(System.in).nextInt();

                switch (choice) {
                    case 1:
                        InputStats(temp);
                        gemstones.add(new Gemstone(temp.get(0).toString(), (Double)temp.get(1), (Double)temp.get(2), (Double)temp.get(3)));
                        temp.clear();
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


    // Функция ввода значений основных полей класса Gemstone
    private static void InputStats(ArrayList<Object> temp){
        boolean flag = true;

        do {
            try{
                System.out.print("Введите название камня :\t");
                temp.add(new Scanner(System.in).nextLine());
                flag = false;
            }catch (Exception e){
                System.out.println("Ошибка!!! Повторите ввод.");
            }
        }while (flag);

        flag = true;
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

    // Функция для вывода подробной информации о камнях
    private static void GemOutput(ArrayList<Gemstone> gemstones){
        if (gemstones.size() != 0){
            System.out.println("\t\t\t\t\tСписок камней");

            for (Gemstone gem : gemstones){
                System.out.println("Тип камня : " + gem.getGemName() + " Вес камня : " + gem.getCarat() + " карат. Стоимость : " +
                        gem.getGemCost() + " Прозрачность : " + gem.getGemTransperancy());
            }
        }
        else{
            System.out.println("\nКамни отстутствуют\n");
        }

    }
}
