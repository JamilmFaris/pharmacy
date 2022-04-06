package com.example.drugstry3.Model;

public class City {
    String CityID, CtName, notes, state, dateCreated;

    public City(String cityID, String ctName, String notes, String state, String dateCreated) {
        CityID = cityID;
        CtName = ctName;
        this.notes = notes;
        this.state = state;
        this.dateCreated = dateCreated;
    }

    public String getCityID() {
        return CityID;
    }

    public void setCityID(String cityID) {
        CityID = cityID;
    }

    public String getCtName() {
        return CtName;
    }

    public void setCtName(String ctName) {
        CtName = ctName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
}
