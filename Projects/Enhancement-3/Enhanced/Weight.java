package com.example.weighttrackingoml;

public class Weight {

    private String _id;
    private double weight;
    private String unit;
    private String date;

    public Weight(double weight, String unit, String date) {
        this.weight = weight;
        this.unit = unit;
        this.date = date;
    }

    public String getId() {
        return _id;
    }

    public double getWeight() {
        return weight;
    }

    public String getUnit() {
        return unit;
    }

    public String getDate() {
        return date;
    }
}
