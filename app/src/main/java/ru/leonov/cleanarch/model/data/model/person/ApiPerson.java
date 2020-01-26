package ru.leonov.cleanarch.model.data.model.person;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiPerson {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("username")
    @Expose
    public ApiUserName username;
    @SerializedName("realname")
    @Expose
    public ApiUserName realname;
    @SerializedName("stat")
    @Expose
    public String stat;
}