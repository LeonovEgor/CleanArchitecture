package ru.leonov.cleanarch.model.data.model.photos;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApiPhotos {

    @SerializedName("page")
    @Expose
    private Integer page;

    @SerializedName("pages")
    @Expose
    private Integer pages;

    @SerializedName("perpage")
    @Expose
    private Integer perpage;

    @SerializedName("total")
    @Expose
    private String total;

    @SerializedName("photo")
    @Expose
    private List<ApiPhoto> photoList = null;

    public Integer getPage() {
        return page;
    }

    public Integer getPages() {
        return pages;
    }

    public Integer getPerpage() {
        return perpage;
    }

    public String getTotal() {
        return total;
    }

    public List<ApiPhoto> getPhotoList() {
        return photoList;
    }
}