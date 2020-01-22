package ru.leonov.cleanarch.view.ui.PhotoList;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.leonov.cleanarch.model.data.PhotoPositionalDataSource;
import ru.leonov.cleanarch.model.data.PhotoRepository;
import ru.leonov.cleanarch.view.ui.PhotoViewModel;

@SuppressWarnings("unchecked")
public class PhotoViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        PhotoRepository repository = new PhotoRepository();
        PhotoPositionalDataSource dataSource = new PhotoPositionalDataSource(repository);

        if (modelClass == PhotoViewModel.class) {
            return (T) new PhotoViewModel(dataSource);
        } else {
            throw new IllegalArgumentException("model class: " + modelClass);
        }
    }
}
