package com.example.opdconsultations.data;

public class Appointment {

    private String address;
    private String distance;
    private String aname;
    private String rating;

    public Appointment(String address, String distance, String aname, String rating) {
        this.address = address;
        this.distance = distance;
        this.aname = aname;
        this.rating = rating;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getName() {
        return aname;
    }

    public void setName(String name) {
        this.aname = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
