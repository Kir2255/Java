package com.db;

public class TravelList {
    private int travelListID;
    private int tourID;
    private int customerID;
    private int travelAgentID;
    private double cost;

    public TravelList(int travelListID, int tourID, int customerID, int travelAgentID, double cost) {
        this.travelListID = travelListID;
        this.tourID = tourID;
        this.customerID = customerID;
        this.travelAgentID = travelAgentID;
        this.cost = cost;
    }

    public int getTravelListID() {
        return travelListID;
    }

    public int getTourID() {
        return tourID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getTravelAgentID() {
        return travelAgentID;
    }

    public double getCost() {
        return cost;
    }
}
