package com.db;

public class TravelList {
    private int travelListID;
    private int tourID;
    private int customerID;
    private int travelAgentID;
    private double cost;
    private double sale;

    public TravelList(int travelListID, int tourID, int customerID, int travelAgentID, double cost, double sale) {
        this.travelListID = travelListID;
        this.tourID = tourID;
        this.customerID = customerID;
        this.travelAgentID = travelAgentID;
        this.cost = cost;
        this.sale = sale;
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

    public double getSale() { return sale; }

    @Override
    public String toString() {
        return "\n TravelList{" +
                "travelListID=" + travelListID +
                ", tourID=" + tourID +
                ", customerID=" + customerID +
                ", travelAgentID=" + travelAgentID +
                ", cost=" + cost +
                ", sale=" + sale +
                '}';
    }
}
