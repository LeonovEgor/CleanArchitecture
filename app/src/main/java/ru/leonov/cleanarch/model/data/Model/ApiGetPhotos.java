package ru.leonov.cleanarch.model.data.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiGetPhotos {

    @SerializedName("photos")
    @Expose
    private ApiPhotos photos;
    @SerializedName("stat")
    @Expose
    private String stat;

    public ApiPhotos getPhotos() {
        return photos;
    }

    public String getStat() {
        return stat;
    }
}
