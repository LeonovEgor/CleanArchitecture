package ru.leonov.cleanarch.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.leonov.cleanarch.model.data.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.data.PhotoRepository2;
import ru.leonov.cleanarch.model.interactor.photos.IPhotoInteractor;
import ru.leonov.cleanarch.model.interactor.photos.PhotoInteractor;
import ru.leonov.cleanarch.viewmodel.PhotoViewModel;

public class PhotoViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
//        IPhotoDataSource dataSource = new PhotoDataSource();
//        IPhotoRepository photoRepository = new PhotoRepository(dataSource);
//        IPhotoInteractor interactor = new PhotoInteractor(photoRepository) ;
        PhotoRepository2 repository = new PhotoRepository2();
        PhotoPositionalDataSource dataSource = new PhotoPositionalDataSource(repository);

        IPhotoInteractor interactor = new PhotoInteractor(photoRepository) ;

        if (modelClass == PhotoViewModel.class) {
            return (T) new PhotoViewModel(Schedulers.io(), AndroidSchedulers.mainThread(), interactor);
        } else {
            throw new IllegalArgumentException("model class: " + modelClass);
        }
    }
}
