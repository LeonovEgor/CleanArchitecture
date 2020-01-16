package ru.leonov.cleanarch.view;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.leonov.cleanarch.model.data.IPhotoDataSource;
import ru.leonov.cleanarch.model.data.PhotoDataSource;
import ru.leonov.cleanarch.model.data.PhotoRepository;
import ru.leonov.cleanarch.model.interactor.photos.IPhotoInteractor;
import ru.leonov.cleanarch.model.interactor.photos.PhotoInteractor;
import ru.leonov.cleanarch.model.repository.IPhotoRepository;
import ru.leonov.cleanarch.viewmodel.PhotoViewModel;

/**
 * Фабрика для создания ViewModel с нестандартным конструктором
 * Если ваша ViewModel не использует параметры конструктора фабрика не нужна
 */
public class PhotoViewModelFactory implements ViewModelProvider.Factory {
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        IPhotoDataSource dataSource = new PhotoDataSource();
        IPhotoRepository photoRepository = new PhotoRepository(dataSource);
        IPhotoInteractor interactor = new PhotoInteractor(photoRepository) ;

        if (modelClass == PhotoViewModel.class) {
            return (T) new PhotoViewModel(Schedulers.io(), AndroidSchedulers.mainThread(), interactor);
        } else {
            throw new IllegalArgumentException("model class: " + modelClass);
        }
    }
}
