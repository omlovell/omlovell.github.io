package com.example.weighttrackingoml;

public class WeightModel {

    private int id;
    private static int total = 0;
    private String weight;
    private String date;

    //Constructors
    public WeightModel(String weight, String date) {

        this.id = ++total;
        this.weight = weight;
        this.date = date;
    }

    public WeightModel() {
    }

    // getter and setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    //to string method


    @Override
    public String toString() {
        return "User Weight {" +
                " Weight='" + weight + '\'' +
                ", Date='" + date + '\'' +
                '}';
    }

}
