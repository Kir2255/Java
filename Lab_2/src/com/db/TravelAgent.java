package com.db;

public class TravelAgent {
    private int travelAgentID;
    private String fio;

    public TravelAgent(int travelAgentID, String fio) {
        this.travelAgentID = travelAgentID;
        this.fio = fio;
    }

    public int getTravelAgentID() {
        return travelAgentID;
    }

    public String getFio() {
        return fio;
    }

    @Override
    public String toString() {
        return "\n TravelAgent{" +
                "travelAgentID=" + travelAgentID +
                ", fio='" + fio + '\'' +
                '}';
    }
}
