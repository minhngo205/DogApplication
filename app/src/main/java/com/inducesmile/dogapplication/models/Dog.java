package com.inducesmile.dogapplication.models;

import com.google.gson.annotations.SerializedName;

public class Dog {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("origin")
    private String origin;
    @SerializedName("life_span")
    private String lifespan;
    @SerializedName("temperament")
    private String temperament;
    @SerializedName("url")
    private String imageURL;
    @SerializedName("bred_for")
    private String bredFor;
    @SerializedName("breed_group")
    private String breedGroup;

    public Dog(int id, String name, String origin, String lifespan, String temperament, String imageURL, String bredFor, String breedGroup) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.lifespan = lifespan;
        this.temperament = temperament;
        this.imageURL = imageURL;
        this.bredFor = bredFor;
        this.breedGroup = breedGroup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        if(origin==null || origin.isEmpty()) return "Unknown origin";
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getLifespan() {
        return lifespan;
    }

    public void setLifespan(String lifespan) {
        this.lifespan = lifespan;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getBreedGroup() {
        if(breedGroup==null || breedGroup.isEmpty()) return "Unknown breed group";
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getBredFor() {
        if(bredFor==null || bredFor.isEmpty()) return "Unknown bred for";
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }
}
