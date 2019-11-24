package com.lab4;

import com.lab4.parking.Car;
import com.lab4.parking.Parking;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int userChoice = 0;

        while (true){
            System.out.print("Введите количество мест на парковке:  ");
            try {
                userChoice = new Scanner(System.in).nextInt();
                if (userChoice > 0){
                    break;
                }
                else {
                    System.out.println("Размер парковки должен быть положительным!!!");
                    continue;
                }
            }catch (Exception e) {
                System.out.println("Ошибка!!! Повторите ввод!!!");
                continue;
            }
        }

        Parking parking = new Parking(userChoice);

        while (true){
            System.out.println("\t\t\t\tМеню");
            System.out.println("1. Припарковать машину");
            System.out.println("2. Отогнать машину");
            System.out.println("3. Посмотреть парковку");
            System.out.println("4. Выход");
            System.out.print("Выберите пункт меню:    ");

            try {
                userChoice = new Scanner(System.in).nextInt();
            }catch (Exception e){
                System.out.println("Ошибка!!! Повторите ввод!!!");
            }

            switch (userChoice){
                case 1:
                    System.out.print("Введите название машины:  ");
                    parking.addCar(new Car(new Scanner(System.in).nextLine()));
                    break;
                case 2:
                    System.out.print("Введите название машины:  ");
                    parking.removeCar(new Car(new Scanner(System.in).nextLine()));
                    break;
                case 3:
                    parking.parkingInfo();
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный ввод!!!");
                    break;
            }
        }
    }
}
