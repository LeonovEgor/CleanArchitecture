package ru.leonov.cleanarch.view.ui.details;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ru.leonov.cleanarch.model.data.person.PersonDataSource;
import ru.leonov.cleanarch.model.data.person.PersonRepository;

@SuppressWarnings("unchecked")
public class DetailViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        PersonRepository repository = new PersonRepository();
        PersonDataSource dataSource = new PersonDataSource(repository);

        if (modelClass == DetailViewModel.class) {
            return (T) new DetailViewModel(dataSource);
        } else {
            throw new IllegalArgumentException("model class: " + modelClass);
        }
    }
}
