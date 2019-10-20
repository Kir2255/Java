package com.main;

import com.db.*;

import java.sql.*;


public class Main {
    public static final String DB_CONNECTION_URL = "jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=TravelAgency;integratedSecurity=true";

    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DB_CONNECTION_URL);
            System.out.println("Соединение установлено");
            try (Statement statement = connection.createStatement()){
                ResultSet resultSet = statement.executeQuery("SELECT * FROM Customers");
                while (resultSet.next()){
                    String s1 = resultSet.getString(1);
                    String s2 = resultSet.getString(2);
                    String s3 = resultSet.getString(3);
                    System.out.println("ID: " + s1 + " FIO: " + s2 + " RegularClient: " + s3);
                }

                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Ошибка при установке соединения");
        }

    }
}
