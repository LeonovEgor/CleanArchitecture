package ru.leonov.cleanarch.presenter;

import ru.leonov.cleanarch.model.entities.PhotoContainer;

public interface IPhotosPresenter {
    void showDetails(PhotoContainer photoContainer);
    void onStart();
}
