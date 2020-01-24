package ru.leonov.cleanarch.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.leonov.cleanarch.CleanArch;
import ru.leonov.cleanarch.model.data.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.presenter.IPhotosPresenter;
import ru.leonov.cleanarch.presenter.PhotosPresenter;

public class PhotoListViewModel extends ViewModel {
    private static final String SAVE_SEARCH_STRING = "search_string";

    IPhotosPresenter photosPresenter;

    private MutableLiveData<String > searchLiveData;
    private MutableLiveData<String> errorLiveData;
    private MutableLiveData<String> resultLiveData;

    private String searchString = "";

    private final PhotoPositionalDataSource dataSource;


    public PhotoListViewModel(PhotoPositionalDataSource dataSource) {
        this.dataSource = dataSource;

        this.searchLiveData = new MutableLiveData<>();
        this.errorLiveData = new MutableLiveData<>();
        this.resultLiveData = new MutableLiveData<>();

        photosPresenter = new PhotosPresenter(CleanArch.getInstance().getNavigator());
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            searchString = savedInstanceState.getString(SAVE_SEARCH_STRING, "");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVE_SEARCH_STRING, searchString);
    }

    public void onPhotoClick(PhotoContainer photoContainer) {
        photosPresenter.showDetails(photoContainer);

    }

    public LiveData<String> getError() {
        return errorLiveData;
    }

    public LiveData<String> getSearchString() {
        return searchLiveData;
    }

    public void setSearchString(String str) {
        dataSource.setSearchString(str);
        searchLiveData.setValue(str);
    }

    public LiveData<String> getResult() { return resultLiveData;  }

    public void setResult(String str) { resultLiveData.setValue(str);  }

    public PhotoPositionalDataSource getDataSource() { return dataSource;  }

    public IPhotosPresenter getPhotoPresenter() {return photosPresenter;}
}