package ru.leonov.cleanarch.model.entities;

import androidx.annotation.NonNull;

public class PhotoContainer {
    private String name;
    private String description;
    private String photoUrl;

    public PhotoContainer(String name, String description, String photoUrl) {
        this.name = name;
        this.description = description;
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    @NonNull
    @Override
    public String toString() {
        return  "PhotoContainer { " +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoUrl='" + photoUrl + '\'' + "}";
    }
}