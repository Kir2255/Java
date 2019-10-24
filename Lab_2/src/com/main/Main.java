package com.main;

import com.db.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.*;


public class Main {
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost/TravelAgency?serverTimezone=Europe/Moscow&useSSL=false";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "AlGeBRa267311226";
    //private static final String DB_PASSWORD = "MYSQL_Razor228F-8";

    private static final String SQL_ADD_CUSTOMER = "INSERT Customers (FIO, RegularClient) VALUES(?, ?)";
    private static final String SQL_ADD_TOUR = "INSERT TourTypes (Type, HotTour) VALUES(?, ?)";
    private static final String SQL_ADD_TRAVEL_AGENT = "INSERT TravelAgents (FIO) VALUES(?)";
    private static final String SQL_ADD_TRAVEL = "INSERT TravelLists (TourID, CustomerID, TravelAgentID, Cost, Sale)" +
            " VALUES(?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_CUSTOMERS = "SELECT * FROM Customers";
    private static final String SQL_SELECT_TOURS = "SELECT * FROM TourTypes";
    private static final String SQL_SELECT_TRAVEL_AGENTS = "SELECT * FROM TravelAgents";
    private static final String SQL_SELECT_TRAVELS = "SELECT * FROM TravelLists";

    private static final String SQL_DELETE_CUSTOMERS = "DELETE FROM Customers WHERE CustomerID = ?";
    private static final String SQL_DELETE_TOURS = "DELETE FROM TourTypes WHERE TourID = ?";
    private static final String SQL_DELETE_TRAVEL_AGENTS = "DELETE FROM TravelAgents WHERE TravelAgentID = ?";
    private static final String SQL_DELETE_TRAVELS = "DELETE FROM TravelLists WHERE TravelListID = ?";

    private static List<TravelAgent> travelAgents = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<TourType> tourTypes = new ArrayList<>();
    private static List<TravelList> travelLists = new ArrayList<>();

    private static List<Connection> connections = new ArrayList<>();

    public static void main(String[] args) {
        initConnections(10); // Иницируем соединения
        Connection connection = getConnection(); // Берем соединение из пула соединений

        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            System.out.println("Соединение установлено");

            PreparedStatement preparedStatement = null;
            Statement statement = connection.createStatement();

            initData(statement); // Инициализируем списки данными из таблиц БД

            boolean globalFlag = true;
            boolean tempFlag;

            do {
                // Отображаеие начальное меню пользователя
                menu("Меню", new String[]{"Добавить данные", "Вывести данные", "Удалить данные"}, true);

                try {
                    Scanner scanner = new Scanner(System.in);
                    int userChoice = scanner.nextInt();

                    switch (userChoice){
                        case 1:
                            boolean firstFlag = true;
                            do {
                                menu("Добавить", new String[]{"Клиента", "Тур", "Турагента", "Оплаченный тур"}, true);
                                try {
                                    scanner = new Scanner(System.in);
                                    userChoice = scanner.nextInt();

                                    switch (userChoice){
                                        case 1:
                                            preparedStatement = connection.prepareStatement(SQL_ADD_CUSTOMER);

                                            // Считываем фамилию Заказчика
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

                                            // Указываем, является ли закачик постоянным клиентом
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

                                            // Выполняем добавление Заказчика в БД
                                            int rows = preparedStatement.executeUpdate();

                                            System.out.printf("\n %d rows added", rows);
                                            break;
                                        case 2:
                                            preparedStatement = connection.prepareStatement(SQL_ADD_TOUR);

                                            // Считываем название Тура
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

                                            // Указываем, является ли тур "горячим"
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

                                            // Добавляем Тур в БД
                                            rows = preparedStatement.executeUpdate();

                                            System.out.printf("\n %d rows added", rows);
                                            break;
                                        case 3:
                                            preparedStatement = connection.prepareStatement(SQL_ADD_TRAVEL_AGENT);

                                            // считываем ФИО Турагента
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

                                            // Добавляем Турагента в БД
                                            rows = preparedStatement.executeUpdate();

                                            System.out.printf("\n %d rows added", rows);
                                            break;
                                        case 4:
                                            initData(statement);

                                            preparedStatement = connection.prepareStatement(SQL_ADD_TRAVEL);

                                            // Выводим список находящихся в БД заказчиков
                                            String[] temp = new String[customers.size()];
                                            for(int i = 0; i < customers.size(); i++){
                                                temp[i] = customers.get(i).getFio();
                                            }
                                            menu("Заказчик", temp, false);

                                            // Считываем заказчика, которого выбрал пользователь
                                            scanner = new Scanner(System.in);
                                            int choice = scanner.nextInt();
                                            preparedStatement.setInt(2, customers.get(choice - 1).getCustomerID());

                                            // Если заказчик является постоянным клиентом, то устанавливаем случайную скидку,
                                            // если - нет, то скидка равна 0
                                            if (customers.get(choice - 1).isRegularClient()){
                                                preparedStatement.setDouble(5, getRandom(0, 100));
                                            }else {
                                                preparedStatement.setDouble(5, 0);
                                            }

                                            // Ввыодим список находящихся в БД туров
                                            temp = new String[tourTypes.size()];
                                            for(int i = 0; i < tourTypes.size(); i++){
                                                temp[i] = tourTypes.get(i).getTour();
                                            }
                                            menu("Тур", temp, false);

                                            // Считываем тур, который выбрал пользователь
                                            scanner = new Scanner(System.in);
                                            preparedStatement.setInt(1, tourTypes.get(scanner.nextInt() - 1).getTourID());

                                            // Если кол-во турагентов > 1, то задаем случайное значение TravelAgentID в
                                            // диапазоне [min; max], в противном случае - TravelAgentId равен ID первого объекта
                                            // в списке travelAgents
                                            if (travelAgents.size() > 1){
                                                preparedStatement.setInt(3, (int) getRandom(travelAgents.get(0).getTravelAgentID(),
                                                        travelAgents.get(travelAgents.size()-1).getTravelAgentID()));
                                            }
                                            else {
                                                preparedStatement.setInt(3, travelAgents.get(0).getTravelAgentID());
                                            }

                                            preparedStatement.setDouble(4, getRandom(100, 450));

                                            // Выполняем добавление Оплаченного тура в БД
                                            preparedStatement.executeUpdate();
                                            break;
                                        case 0:
                                            firstFlag = false;
                                            break;
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    System.out.println("Ошибка!!! Повторите ввод!!!");
                                    firstFlag = true;
                                }
                            } while (firstFlag);
                            break;
                        case 2:
                            boolean secondFlag = true;
                            do {
                                menu("Вывести", new String[]{"Клиенты", "Туры", "Турагенты", "Оплаченные туры"}, true);
                                try {
                                    scanner = new Scanner(System.in);
                                    userChoice = scanner.nextInt();

                                    switch (userChoice){
                                        case 1:
                                            customers.clear();
                                            createEntity(statement, SQL_SELECT_CUSTOMERS, 1);

                                            // Выводим список customers
                                            if (customers.size() != 0){
                                                customers.forEach(el-> System.out.println(el.toString()));
                                            }else {
                                                System.out.println("Данные отсутствуют");
                                            }

                                            break;
                                        case 2:
                                            tourTypes.clear();
                                            createEntity(statement, SQL_SELECT_TOURS,2);

                                            // Выводим список tourTypes
                                            if (tourTypes.size() != 0){
                                                tourTypes.forEach(el->System.out.println(el.toString()));
                                            }else {
                                                System.out.println("Данные отсутствуют");
                                            }
                                            break;
                                        case 3:
                                            travelAgents.clear();
                                            createEntity(statement, SQL_SELECT_TRAVEL_AGENTS,3);

                                            // Выводим список travelAgents
                                            if (travelAgents.size() != 0){
                                                travelAgents.forEach(el->System.out.println(el.toString()));
                                            }else {
                                                System.out.println("Данные отсутствуют");
                                            }
                                           break;
                                        case 4:
                                            travelLists.clear();
                                            createEntity(statement, SQL_SELECT_TRAVELS, 4);

                                            // Выводим список travelLists
                                            if (travelLists.size() != 0){
                                                travelLists.forEach(el->System.out.println(el.toString()));
                                            }else {
                                                System.out.println("Данные отсутствуют");
                                            }
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
                            boolean thirdFlag = true;
                            do {
                                menu("Удалить", new String[]{"Клиенты", "Туры", "Турагенты", "Оплаченные туры"}, true);
                                initData(statement);
                                try {
                                    scanner = new Scanner(System.in);
                                    userChoice = scanner.nextInt();

                                    switch (userChoice){
                                        case 1:
                                            preparedStatement = connection.prepareStatement(SQL_DELETE_CUSTOMERS);

                                            String[] temp = new String[customers.size()];
                                            for(int i = 0; i < customers.size(); i++){
                                                temp[i] = customers.get(i).getFio();
                                            }
                                            menu("Заказчик", temp, false);

                                            try {
                                                scanner = new Scanner(System.in);
                                                preparedStatement.setInt(1, customers.get(scanner.nextInt() - 1).getCustomerID());
                                                preparedStatement.executeUpdate();
                                            }catch (Exception e){
                                                System.out.println("Ошибка!!!");
                                            }
                                            break;
                                        case 2:
                                            preparedStatement = connection.prepareStatement(SQL_DELETE_TOURS);

                                            temp = new String[tourTypes.size()];
                                            for(int i = 0; i < tourTypes.size(); i++){
                                                temp[i] = tourTypes.get(i).getTour();
                                            }
                                            menu("Тур", temp, false);

                                            try {
                                                scanner = new Scanner(System.in);
                                                preparedStatement.setInt(1, tourTypes.get(scanner.nextInt() - 1).getTourID());
                                                preparedStatement.executeUpdate();
                                            }catch (Exception e){
                                                System.out.println("Ошибка!!!");
                                            }
                                            break;
                                        case 3:
                                            preparedStatement = connection.prepareStatement(SQL_DELETE_TRAVEL_AGENTS);

                                            temp = new String[travelAgents.size()];
                                            for(int i = 0; i < travelAgents.size(); i++){
                                                temp[i] = travelAgents.get(i).getFio();
                                            }
                                            menu("Турагент", temp, false);

                                            try {
                                                scanner = new Scanner(System.in);
                                                preparedStatement.setInt(1, travelAgents.get(scanner.nextInt() - 1).getTravelAgentID());
                                                preparedStatement.executeUpdate();
                                            }catch (Exception e){
                                                System.out.println("Ошибка!!!");
                                            }
                                            break;
                                        case 4:
                                            preparedStatement = connection.prepareStatement(SQL_DELETE_TRAVELS);

                                            System.out.print(" Введите TravelListID:\t");
                                            try {
                                                scanner = new Scanner(System.in);
                                                preparedStatement.setInt(1, scanner.nextInt());
                                                preparedStatement.executeUpdate();
                                            }catch (Exception e){
                                                System.out.println("Ошибка!!!");
                                            }
                                            break;
                                        case 0:
                                            thirdFlag = false;
                                            break;
                                    }
                                }catch (Exception e){
                                    e.printStackTrace();
                                    System.out.println("Ошибка!!! Повторите ввод!!!");
                                    thirdFlag = true;
                                }
                            } while (thirdFlag);
                            break;
                        case 0:
                            globalFlag = false;
                            break;
                    }
                }catch (Exception e){
                    System.out.println("Ошибка!!! Повторите ввод!!!");
                }
            } while (globalFlag);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при установке соединения");
        } finally {
            // Возвращаем текущее соединение в пул соединений и закрываем их
            putConnection(connection);
            closeConnections();
        }
    }

    /// Отображение пунктов меню. В качестве параметров передаеются заголовок,
    /// пункты меню и булевская переменна, отвечающая за вывод особого пункта меню.
    private static void menu(String title, String[] menuItems, boolean flag){
        System.out.println("\n\t\t" + title);
        for (int i = 0; i < menuItems.length; i++){
            System.out.println(" " + (i + 1) + ". " + menuItems[i]);
        }
        if (flag){
            System.out.println(" 0. Выход");
        }
        System.out.print("\n Пункт меню:\t");
    }

    /// Возвращает ResultSet выборки из таблицы БД. В качестве параметров принимает существующий statement
    /// и код SQL-запроса
    private static ResultSet returnResult(Statement statement, String SQL) throws SQLException{
        return statement.executeQuery(SQL);

    }

    /// По ResultSet'у создает экземпляры тех или иных объектов. В качестве параметров принимает существующий statement,
    /// код SQL-запроса и int переменную, отвечающую за выбор создаваемого объекта
    private static void createEntity(Statement statement, String SQL, int i) throws SQLException {
        ResultSet set = returnResult(statement, SQL);

        if (set != null){
            while (set.next()){
                switch (i){
                    case 1:
                        customers.add(new Customer(set.getInt("CustomerID"), set.getString("FIO"),
                                set.getBoolean("RegularClient")));
                        break;
                    case 2:
                        tourTypes.add(new TourType(set.getInt("TourID"), set.getString("Type"),
                                set.getBoolean("HotTour")));
                        break;
                    case 3:
                        travelAgents.add(new TravelAgent(set.getInt("TravelAgentID"), set.getString("FIO")));
                        break;
                    case 4:
                        travelLists.add(new TravelList(set.getInt("TravelListID"), set.getInt("TourID"),
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

    /// Вовзращает рандомное значение в пределах [min; max]
    private static double getRandom(double min, double max){
        return (Math.random() * ((max - min) + 1) + min);
    }

    /// Иницирует соединения к БД
    private static void initConnections(int n) {
        for (int i = 0; i < n; i++) {
            try {
                connections.add(DriverManager.getConnection(DB_CONNECTION_URL, DB_USERNAME, DB_PASSWORD));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /// Возращает соединение из пула соединений БД
    private static Connection getConnection() {
        if (connections.size() == 0) {
            return null;
        }

        Connection tempConnection = connections.get(0);
        connections.remove(0);
        return tempConnection;
    }

    /// Добавляет экземпляр соедниения в пул соединений
    private static void putConnection(Connection connection) {
        connections.add(connection);
    }

    /// Закрывает все текущие соединения, находящиеся в пуле соединений
    private static void closeConnections() {
        for (Connection connection : connections) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /// Начальная инициализация данными из таблиц БД
    private static void initData(Statement statement){
        customers.clear();
        try {
            createEntity(statement, SQL_SELECT_CUSTOMERS, 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        tourTypes.clear();
        try {
            createEntity(statement, SQL_SELECT_TOURS,2);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        travelAgents.clear();
        try {
            createEntity(statement, SQL_SELECT_TRAVEL_AGENTS,3);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        travelLists.clear();
        try {
            createEntity(statement, SQL_SELECT_TRAVELS, 4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
