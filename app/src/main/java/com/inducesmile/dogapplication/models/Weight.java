package com.inducesmile.dogapplication.models;

public class Weight {
    private String imperial;
    private String metric;

    // Getter Methods
    public String getImperial() {
        return imperial+ " pound";
    }

    public String getMetric() {
        return metric+" kg";
    }

    // Setter Methods
    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}