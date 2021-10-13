package com.inducesmile.dogapplication.models;

public class Height {
    private String imperial;
    private String metric;


    // Getter Methods
    public String getImperial() {
        return imperial+ " inch";
    }

    public String getMetric() {
        return metric+" cm";
    }

    // Setter Methods
    public void setImperial(String imperial) {
        this.imperial = imperial;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }
}