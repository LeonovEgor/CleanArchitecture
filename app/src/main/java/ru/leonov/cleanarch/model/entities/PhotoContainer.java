package ru.leonov.cleanarch.model.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class PhotoContainer implements Serializable {
    private final String userId;
    private final String description;
    private final String photoUrl;
    private final String bigPhotoUrl;
    private String name;

    public PhotoContainer(String userId, String description, String photoUrl, String bigPhotoUrl) {
        this.userId = userId;
        this.description = description;
        this.photoUrl = photoUrl;
        this.bigPhotoUrl = bigPhotoUrl;
        this.name = ""; // Инициализация только в случае вызова
    }

    public String getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getBigPhotoUrl() {
        return bigPhotoUrl;
    }

    public String getName() { return name; }
    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return  "PhotoContainer { " +
                "name='" + userId + '\'' +
                ", description='" + description + '\'' +
                ", photoUrl='" + photoUrl + '\'' + "}" +
                ", bigPhotoUrl='" + bigPhotoUrl + '\'' + "}";
    }
}