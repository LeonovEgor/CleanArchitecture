package ru.leonov.cleanarch.view.ui.photoList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.leonov.cleanarch.CleanArch;
import ru.leonov.cleanarch.model.data.photo.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.presenter.IPhotosPresenter;
import ru.leonov.cleanarch.presenter.PhotosPresenter;

public class PhotoListViewModel extends ViewModel {

    private final IPhotosPresenter photosPresenter;

    private MutableLiveData<String > searchLiveData;
    private MutableLiveData<String> errorLiveData;
    private MutableLiveData<String> resultLiveData;

    private final PhotoPositionalDataSource dataSource;


    PhotoListViewModel(PhotoPositionalDataSource dataSource) {
        this.dataSource = dataSource;

        this.searchLiveData = new MutableLiveData<>();
        this.errorLiveData = new MutableLiveData<>();
        this.resultLiveData = new MutableLiveData<>();

        photosPresenter = new PhotosPresenter(CleanArch.getInstance().getNavigator());
    }

    void onPhotoClick(PhotoContainer photoContainer) {
        photosPresenter.showDetails(photoContainer);
    }

    public LiveData<String> getError() {
        return errorLiveData;
    }

    public LiveData<String> getSearchString() {
        return searchLiveData;
    }

    void setSearchString(String str) {
        dataSource.setSearchString(str);
        searchLiveData.setValue(str);
    }

    public LiveData<String> getResult() { return resultLiveData;  }

    void setResult(String str) { resultLiveData.setValue(str);  }

    PhotoPositionalDataSource getDataSource() { return dataSource;  }
}