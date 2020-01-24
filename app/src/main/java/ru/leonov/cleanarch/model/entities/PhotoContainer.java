package ru.leonov.cleanarch.model.entities;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class PhotoContainer implements Serializable {
    public String name;
    public String description;
    public String photoUrl;

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