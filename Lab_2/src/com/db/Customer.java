package com.db;

public class Customer {
    private int customerID;
    private String fio;
    private boolean regularClient;

    public Customer(int customerID, String fio, boolean regularClient) {
        this.customerID = customerID;
        this.fio = fio;
        this.regularClient = regularClient;
    }

    public int getCustomerID() {
        return customerID;
    }

    public String getFio() {
        return fio;
    }

    public boolean isRegularClient() {
        return regularClient;
    }

    @Override
    public String toString() {
        return "\n Customer{" +
                "customerID=" + customerID +
                ", fio='" + fio + '\'' +
                ", regularClient=" + regularClient +
                '}';
    }
}
