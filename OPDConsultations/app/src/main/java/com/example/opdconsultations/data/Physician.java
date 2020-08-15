package com.example.opdconsultations.data;

public class Physician {

    private String pnum;
    private String rating;
    private String name;
    private String degrees;

    public Physician()
    {}

    public Physician(String pnum, String rating, String pname, String degrees) {
        this.pnum = pnum;
        this.rating = rating;
        this.name = pname;
        this.degrees = degrees;
    }

    public String getPnum() {
        return pnum;
    }

    public void setPnum(String pnum) {
        this.pnum = pnum;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getPname() {
        return name;
    }

    public void setPname(String pname) {
        this.name = pname;
    }

    public String getDegrees() {
        return degrees;
    }

    public void setDegrees(String degrees) {
        this.degrees = degrees;
    }
}
