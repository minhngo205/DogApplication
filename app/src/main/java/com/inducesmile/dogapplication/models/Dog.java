package com.inducesmile.dogapplication.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import com.google.gson.annotations.SerializedName;
import com.inducesmile.dogapplication.database.entities.DogEntity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Dog implements Parcelable {
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
    @SerializedName("weight")
    private Weight weight;
    @SerializedName("height")
    private Height height;

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

    protected Dog(Parcel in) {
        id = in.readInt();
        name = in.readString();
        origin = in.readString();
        lifespan = in.readString();
        temperament = in.readString();
        imageURL = in.readString();
        bredFor = in.readString();
        breedGroup = in.readString();
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel in) {
            return new Dog(in);
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

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
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getBredFor() {
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public Height getHeight() {
        return height;
    }

    public void setHeight(Height height) {
        this.height = height;
    }

    public Weight getWeight() {
        return weight;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(origin);
        parcel.writeString(lifespan);
        parcel.writeString(temperament);
        parcel.writeString(imageURL);
        parcel.writeString(bredFor);
        parcel.writeString(breedGroup);
    }

    public DogEntity casToEntity(){
        return new DogEntity(
                getId(),
                getName(),
                getOrigin(),
                getLifespan(),
                getTemperament(),
                getBredFor(),
                getBreedGroup(),
                getWeight().getMetric(),
                getWeight().getImperial(),
                getHeight().getMetric(),
                getHeight().getImperial(),
                getImageURL()
        );
    }
}