package com.main;

import java.io.IOError;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean flag = true;
	    do {
	        System.out.println("\t\t\t\t\tМеню");
	        System.out.println("1. Доюавить камни");
            System.out.println("2. Отобрать камни для ожерелья");
            System.out.println("3. Посчитать общий вес камней и их стоимость");
            System.out.println("4. Отсортировать камни ожерелья по ценности");
            System.out.println("5. Поиск камней ожерелья по заданному диапазону прозрачности");

            try {
                System.out.print("\nПункт меню:\t");
                int choice = new Scanner(System.in).nextInt();
            }
            catch (Exception e){
                System.out.println("Ошибка!!! Повторите ввод.");
            }



        }while (flag);
    }
}
