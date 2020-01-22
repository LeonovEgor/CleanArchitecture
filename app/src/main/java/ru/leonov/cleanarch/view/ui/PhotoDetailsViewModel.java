package ru.leonov.cleanarch.view.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ru.leonov.cleanarch.model.data.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.entities.PhotoContainer;

public class PhotoDetailsViewModel extends ViewModel {

    private MutableLiveData<String> errorLiveData;
    private MutableLiveData<PhotoContainer> resultLiveData;

    private final PhotoPositionalDataSource dataSource;


    public PhotoDetailsViewModel(PhotoPositionalDataSource dataSource) {
        this.dataSource = dataSource;

        this.errorLiveData = new MutableLiveData<>();
        this.resultLiveData = new MutableLiveData<>();
    }

    public LiveData<String> getError() {
        return errorLiveData;
    }

    public LiveData<PhotoContainer> getResult() { return resultLiveData;  }

    public void setResult(PhotoContainer photoContainer) { resultLiveData.setValue(photoContainer);  }

    public PhotoPositionalDataSource getDataSource() { return dataSource;  }

    public void onStart() {
        dataSource.
                //TODO: Посмотреть, как сделано во фрагменте 3 и сделать так же передачу параметра. Тут dataSource уже не нужно. 
    }
}