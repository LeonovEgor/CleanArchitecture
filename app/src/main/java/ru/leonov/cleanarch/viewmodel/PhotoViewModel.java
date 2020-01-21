package ru.leonov.cleanarch.viewmodel;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import ru.leonov.cleanarch.model.data.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

public class PhotoViewModel extends ViewModel {
    private static final String SAVE_SEARCH_STRING = "search_string";

    private MutableLiveData<String > searchLiveData;
    private MutableLiveData<String> errorLiveData;
    private MutableLiveData<String> resultLiveData;

    private String searchString = "";

    private final PhotoPositionalDataSource dataSource;


    public PhotoViewModel(PhotoPositionalDataSource dataSource) {
        this.dataSource = dataSource;

        this.searchLiveData = new MutableLiveData<>();
        this.errorLiveData = new MutableLiveData<>();
        this.resultLiveData = new MutableLiveData<>();
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            searchString = savedInstanceState.getString(SAVE_SEARCH_STRING, "");
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        outState.putString(SAVE_SEARCH_STRING, searchString);
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
}