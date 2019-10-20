package com.main;

import com.db.Customer;
import com.db.TourType;
import com.db.TravelAgent;
import com.db.TravelList;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;


public class Main {
    public static final String DB_CONNECTION_URL = "jdbc:mysql://localhost/TravelAgency?serverTimezone=Europe/Moscow&useSSL=false";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "AlGeBRa267311226";

    public static final String SQL_ADD_CUSTOMER = "INSERT Customers (FIO, RegularClient) VALUES(?, ?)";
    public static final String SQL_ADD_TOUR = "INSERT TourTypes (Type, HotTour) VALUES(?, ?)";
    public static final String SQL_ADD_TRAVEL_AGENT = "INSERT TravelAgents (FIO) VALUES(?)";
    public static final String SQL_ADD_TRAVEL = "INSERT TravelLists (TourID, CustomerID, TravelAgentID, Cost, Sale)" +
            " VALUES(?, ?, ?, ?, ?)";

    public static final String SQL_SELECT_CUSTOMERS = "SELECT * FROM Customers";
    public static final String SQL_SELECT_TOURS = "SELECT * FROM TourTypes";
    public static final String SQL_SELECT_TRAVEL_AGENTS = "SELECT * FROM TravelAgents";
    public static final String SQL_SELECT_TRAVELS = "SELECT * FROM TravelLists";

    public static List<TravelAgent> travelAgents = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<TourType> tourTypes = new ArrayList<>();
    public static List<TravelList> travelLists = new ArrayList<>();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection connection = DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD)){
                System.out.println("Соединение установлено");

                PreparedStatement preparedStatement = null;
                ResultSet set;
                boolean globalFlag = true;
                boolean tempFlag = true;

                do {
                    menu("Меню", new String[]{"Добавить данные", "Вывести данные", "Удалить данные"});

                    try {
                        Scanner scanner = new Scanner(System.in);
                        int userChoice = scanner.nextInt();

                        switch (userChoice){
                            case 1:
                                boolean firstFlag = true;
                                do {
                                    menu("Добавить", new String[]{"Клиента", "Тур", "Турагента", "Оплаченный тур"});
                                    try {
                                        scanner = new Scanner(System.in);
                                        userChoice = scanner.nextInt();

                                        switch (userChoice){
                                            case 1:
                                                preparedStatement = connection.prepareStatement(SQL_ADD_CUSTOMER);

                                                do {
                                                    try {
                                                        scanner = new Scanner(System.in);
                                                        System.out.print(" ФИО:\t");
                                                        preparedStatement.setString(1, scanner.next());

                                                        tempFlag = false;
                                                    }catch (Exception e){
                                                        System.out.println("Ошибка!!! Повторите ввод!!!");
                                                        tempFlag = true;
                                                   }
                                                }while (tempFlag);

                                                do {
                                                    try {
                                                        scanner = new Scanner(System.in);
                                                        System.out.print(" Постоянный клиент (true / false):\t");
                                                        preparedStatement.setBoolean(2, scanner.nextBoolean());

                                                        tempFlag = false;
                                                    }catch (Exception e){
                                                        System.out.println("Ошибка!!! Повторите ввод!!!");
                                                        tempFlag = true;
                                                    }
                                                }while (tempFlag);

                                                int rows = preparedStatement.executeUpdate();

                                                System.out.printf("\n %d rows added", rows);
                                                break;
                                            case 2:
                                                preparedStatement = connection.prepareStatement(SQL_ADD_TOUR);

                                                do {
                                                    try {
                                                        scanner = new Scanner(System.in);
                                                        System.out.print(" Тип тура:\t");
                                                        preparedStatement.setString(1, scanner.next());

                                                        tempFlag = false;
                                                    }catch (Exception e){
                                                        System.out.println("Ошибка!!! Повторите ввод!!!");
                                                        tempFlag = true;
                                                    }
                                                }while (tempFlag);

                                                do {
                                                    try {
                                                        scanner = new Scanner(System.in);
                                                        System.out.print(" Горячий тур (true / false):\t");
                                                        preparedStatement.setBoolean(2, scanner.nextBoolean());

                                                        tempFlag = false;
                                                    }catch (Exception e){
                                                        System.out.println("Ошибка!!! Повторите ввод!!!");
                                                        tempFlag = true;
                                                    }
                                                }while (tempFlag);

                                                rows = preparedStatement.executeUpdate();

                                                System.out.printf("\n %d rows added", rows);
                                                break;
                                            case 3:
                                                preparedStatement = connection.prepareStatement(SQL_ADD_TRAVEL_AGENT);

                                                do {
                                                    try {
                                                        scanner = new Scanner(System.in);
                                                        System.out.print(" ФИО:\t");
                                                        preparedStatement.setString(1, scanner.next());

                                                        tempFlag = false;
                                                    }catch (Exception e){
                                                        System.out.println("Ошибка!!! Повторите ввод!!!");
                                                        tempFlag = true;
                                                    }
                                                }while (tempFlag);

                                                rows = preparedStatement.executeUpdate();

                                                System.out.printf("\n %d rows added", rows);
                                                break;
                                            case 4:
                                                break;
                                            case 0:
                                                firstFlag = false;
                                                break;
                                        }
                                    }catch (Exception e){
                                        System.out.println("Ошибка!!! Повторите ввод!!!");
                                        firstFlag = true;
                                    }

                                } while (firstFlag);

                                break;
                            case 2:
                                boolean secondFlag = true;
                                do {
                                    menu("Вывести", new String[]{"Клиенты", "Туры", "Турагенты", "Оплаченные туры"});

                                    Statement statement = connection.createStatement();
                                    try {
                                        scanner = new Scanner(System.in);
                                        userChoice = scanner.nextInt();

                                        switch (userChoice){
                                            case 1:
                                                createEntity(statement, SQL_SELECT_CUSTOMERS, 1);
                                                customers.get(0).toString();
                                                displayData(customers);
                                                break;
                                            case 2:
                                                createEntity(statement, SQL_SELECT_TOURS,2);
                                                displayData(tourTypes);
                                                break;
                                            case 3:
                                               createEntity(statement, SQL_SELECT_TRAVEL_AGENTS,3);
                                               displayData(travelAgents);
                                               break;
                                           case 4:
                                                createEntity(statement, SQL_SELECT_TRAVELS, 4);
                                                displayData(travelLists);
                                                break;
                                            case 0:
                                                secondFlag = false;
                                                break;
                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                        System.out.println("Ошибка!!! Повторите ввод!!!");
                                        secondFlag = true;
                                    }

                            } while (secondFlag);
                                break;
                            case 3:
                                break;
                            case 0:
                                globalFlag = false;
                                break;
                        }
                    }catch (Exception e){
                        System.out.println("Ошибка!!! Повторите ввод!!!");
                    }

                } while (globalFlag);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при установке соединения");
        }
    }

    static void menu(String title, String[] menuItems){
        System.out.println("\n\t\t" + title);
        for (int i = 0; i < menuItems.length; i++){
            System.out.println(" " + (i + 1) + ". " + menuItems[i]);
        }
        System.out.println(" 0. Выход");
        System.out.print("\n Пункт меню:\t");
    }

    static ResultSet returnResult(Statement statement, String SQL) throws SQLException{
        ResultSet resultSet = statement.executeQuery(SQL);
        return  resultSet;

    }

    static void createEntity(Statement statement, String SQL, int i) throws SQLException {
        ResultSet set = returnResult(statement, SQL);
        if (set != null){
            while (set.next()){
                switch (i){
                    case 1:
                        System.out.println(465);
                        customers.clear();
                        customers.add(new Customer(set.getInt("CustomerID"), set.getString("FIO"),
                                set.getBoolean("RegularClient")));
                        break;
                    case 2:
                        tourTypes.clear();
                        tourTypes.add(new TourType(set.getInt("TourID"), set.getString("Type"),
                                set.getBoolean("HotTour")));
                        break;
                    case 3:
                        travelAgents.clear();
                        travelAgents.add(new TravelAgent(set.getInt("TravelAgentID"), set.getString("FIO")));
                        break;
                    case 4:
                        travelLists.clear();
                        travelLists.add(new TravelList(set.getInt("TravelAgentID"), set.getInt("TourID"),
                                set.getInt("CustomerID"), set.getInt("TravelAgentID"), set.getDouble("Cost"),
                                set.getDouble("Sale")));
                        break;
                }
            }
        }
        else{
            System.out.println("ResultSet пустой");
        }
    }

    static void displayData(List<?> temp){
        for(var item : temp){
            item.toString();
        }
    }
}
