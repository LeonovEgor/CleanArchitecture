package ru.leonov.cleanarch.view.ui.photoList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.leonov.cleanarch.model.data.photo.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.data.photo.PhotoRepository;

@SuppressWarnings("unchecked")
public class PhotoViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        PhotoRepository repository = new PhotoRepository();
        PhotoPositionalDataSource dataSource = new PhotoPositionalDataSource(repository);

        if (modelClass == PhotoListViewModel.class) {
            return (T) new PhotoListViewModel(dataSource);
        } else {
            throw new IllegalArgumentException("model class: " + modelClass);
        }
    }
}
