package com.lab4.parking;

public class Parking {
    public Car[] cars;
    private int size;

    public Parking(int N){
        size = N;
        this.cars = new Car[size];
    }

    public Parking(Car[] cars) {
        this.cars = cars;
        size = cars.length;
    }

    public void addCar(Car car){
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null){
                cars[i] = car;
                System.out.println("Машина припарковалась на " + (i + 1) + " месте.");
                return;
            }
        }

        System.out.println("Свободных мест нет!");
    }

    public void removeCar(Car car){
        try {
            for (int i = 0; i < cars.length; i++) {
                if (cars[i].getName().equals(car.getName())){
                    cars[i] = null;
                }
            }
            System.out.println("Машина " + car.getName() + " уехала");
        }catch (Exception e){
            System.out.println("Машины " + car.getName() + " не было на парковке");
        }
    }

    public  void parkingInfo(){
        for (int i = 0; i < size; i++) {
            if (cars[i] != null){
                System.out.println((i + 1) + ". " + cars[i].getName());
            }
            else {
                System.out.println((i + 1) + ". Пусто");
            }
        }
    }
}
