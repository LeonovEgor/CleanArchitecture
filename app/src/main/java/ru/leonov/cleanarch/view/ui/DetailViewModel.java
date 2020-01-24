package ru.leonov.cleanarch.view.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.leonov.cleanarch.model.entities.PhotoContainer;

public class DetailViewModel extends ViewModel {

    private MutableLiveData<PhotoContainer> resultLiveData;

    public DetailViewModel() {
        resultLiveData = new MutableLiveData<>();
    }

    public LiveData<PhotoContainer> getResult() {
        return resultLiveData;
    }

    public void setResult(PhotoContainer photoContainer) {
        resultLiveData.setValue(photoContainer);
    }
}