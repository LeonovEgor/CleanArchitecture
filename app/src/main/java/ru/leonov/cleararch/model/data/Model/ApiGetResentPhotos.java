package ru.leonov.cleararch.model.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiGetResentPhotos {

    @SerializedName("photos")
    @Expose
    private ApiPhotos photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    public ApiPhotos getPhotos() {
        return photos;
    }

    public void setPhotos(ApiPhotos photos) {
        this.photos = photos;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }
}
