package ru.leonov.cleanarch.view.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.leonov.cleanarch.model.data.person.PersonDataSource;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.repository.IResponseCallback;

public class DetailViewModel extends ViewModel implements IResponseCallback {

    private MutableLiveData<PhotoContainer> resultLiveData;
    private MutableLiveData<String> error;

    private final PersonDataSource dataSource;
    private PhotoContainer photoContainer;

    DetailViewModel(PersonDataSource dataSource) {
        resultLiveData = new MutableLiveData<>();
        this.dataSource = dataSource;
    }

    LiveData<PhotoContainer> getResult() {
        return resultLiveData;
    }

    void setResult(PhotoContainer photoContainer) {
        this.photoContainer = photoContainer;
        resultLiveData.setValue(photoContainer);
        dataSource.getPerson(photoContainer.getUserId(), this);
    }

    @Override
    public void response(String name) {
        photoContainer.setName(name);
        resultLiveData.setValue(photoContainer);
    }

    @Override
    public void responseError(String errorMessage) {
        this.error.setValue(errorMessage);
    }

}