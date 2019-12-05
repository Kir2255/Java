package com.lab4.parking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parking {
    public List<Car> cars;
    private int size;

    public Parking(int N){
        size = N;
        this.cars = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            cars.add(i, null);
        }
    }

    public Parking(ArrayList<Car> cars) {
        this.cars = cars;
        size = cars.size();
    }

    public void addCar(Car car){
        for (int i = 0; i < size; i++) {
            if (cars.get(i) == null){
                cars.set(i, car);
                System.out.println("Машина припарковалась на " + (i + 1) + " месте.");
                return;
            }
        }

        System.out.println("Свободных мест нет!");
    }

    public void removeCar(Car car){
        try {
            for (int i = 0; i < size; i++) {
                if (cars.get(i) != null) {
                    if (cars.get(i).getName().equals(car.getName())) {
                        cars.set(i, null);
                    }
                }
            }
            System.out.println("Машина " + car.getName() + " уехала");
        }catch (Exception e){
            System.out.println("Машины " + car.getName() + " не было на парковке");
        }
    }

    public  void parkingInfo(){
        for (int i = 0; i < size; i++) {
            if (cars.get(i) != null){
                System.out.println((i + 1) + ". " + cars.get(i).getName());
            }
            else {
                System.out.println((i + 1) + ". Пусто");
            }
        }
    }
}
