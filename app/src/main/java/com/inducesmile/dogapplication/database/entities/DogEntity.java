package com.inducesmile.dogapplication.database.entities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "dog")
public class DogEntity implements Parcelable {
    @PrimaryKey
    private int id;
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String origin;
    @ColumnInfo
    private String lifespan;
    @ColumnInfo
    private String temperament;
    @ColumnInfo
    private String bredFor;
    @ColumnInfo
    private String breedGroup;
    @ColumnInfo
    private String weightMetric;
    @ColumnInfo
    private String weightImperial;
    @ColumnInfo
    private String heightMetric;
    @ColumnInfo
    private String heightImperial;
    @ColumnInfo
    private String imageURL;
    @ColumnInfo
    private byte[] image;
    private boolean showMenu = false;

    public DogEntity(int id, String name, String origin, String lifespan, String temperament, String bredFor, String breedGroup, String weightMetric, String weightImperial, String heightMetric, String heightImperial, String imageURL) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.lifespan = lifespan;
        this.temperament = temperament;
        this.bredFor = bredFor;
        this.breedGroup = breedGroup;
        this.weightMetric = weightMetric;
        this.weightImperial = weightImperial;
        this.heightMetric = heightMetric;
        this.heightImperial = heightImperial;
        this.imageURL = imageURL;
    }

    protected DogEntity(Parcel in) {
        id = in.readInt();
        name = in.readString();
        origin = in.readString();
        lifespan = in.readString();
        temperament = in.readString();
        bredFor = in.readString();
        breedGroup = in.readString();
        weightMetric = in.readString();
        weightImperial = in.readString();
        heightMetric = in.readString();
        heightImperial = in.readString();
        imageURL = in.readString();
        image = in.createByteArray();
    }

    public static final Creator<DogEntity> CREATOR = new Creator<DogEntity>() {
        @Override
        public DogEntity createFromParcel(Parcel in) {
            return new DogEntity(in);
        }

        @Override
        public DogEntity[] newArray(int size) {
            return new DogEntity[size];
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

    public String getTemperament() {
        return temperament;
    }

    public void setTemperament(String temperament) {
        this.temperament = temperament;
    }

    public String getBredFor() {
        if(bredFor==null || bredFor.isEmpty()) return "Unknown bred for";
        return bredFor;
    }

    public void setBredFor(String bredFor) {
        this.bredFor = bredFor;
    }

    public String getBreedGroup() {
        if(breedGroup==null || breedGroup.isEmpty()) return "Unknown breed group";
        return breedGroup;
    }

    public void setBreedGroup(String breedGroup) {
        this.breedGroup = breedGroup;
    }

    public String getWeightMetric() {
        return weightMetric;
    }

    public void setWeightMetric(String weightMetric) {
        this.weightMetric = weightMetric;
    }

    public String getWeightImperial() {
        return weightImperial;
    }

    public void setWeightImperial(String weightImperial) {
        this.weightImperial = weightImperial;
    }

    public String getHeightMetric() {
        return heightMetric;
    }

    public void setHeightMetric(String heightMetric) {
        this.heightMetric = heightMetric;
    }

    public String getHeightImperial() {
        return heightImperial;
    }

    public void setHeightImperial(String heightImperial) {
        this.heightImperial = heightImperial;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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
        parcel.writeString(bredFor);
        parcel.writeString(breedGroup);
        parcel.writeString(weightMetric);
        parcel.writeString(weightImperial);
        parcel.writeString(heightMetric);
        parcel.writeString(heightImperial);
        parcel.writeString(imageURL);
        parcel.writeByteArray(image);
    }

    public boolean isShowMenu() {
        return showMenu;
    }

    public void setShowMenu(boolean showMenu) {
        this.showMenu = showMenu;
    }
}
