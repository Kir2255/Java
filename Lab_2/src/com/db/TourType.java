package com.db;

public class TourType {
    private int tourID;
    private String tour;
    private boolean totTour;

    public TourType(int tourID, String tour, boolean totTour) {
        this.tourID = tourID;
        this.tour = tour;
        this.totTour = totTour;
    }

    public int getTourID() {
        return tourID;
    }

    public String getTour() {
        return tour;
    }

    public boolean isTotTour() {
        return totTour;
    }
}
