package ru.leonov.cleanarch.model.data.model.person;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiUserName {
    @SerializedName("_content")
    @Expose
    public String content;
}