package ru.leonov.cleanarch.viewmodel;

import androidx.databinding.ObservableField;

import java.util.List;

import ru.leonov.cleanarch.model.entities.PhotoContainer;

/**
 * Интерфейс ViewModel
 * обеспечивает двусторонную связь с View через ObservableField
 */
public interface IPhotoViewModel {

    // методы, которые вызывает View
    // аналогичны методам презентера из MVP
    void onStart();
    void onStop();
    void onSearchPhotoAction();

    // поля данных, на которые подписывается View
    // ObservableField не иммеет отношения к RxJava
    ObservableField<List<PhotoContainer>> getPhotos();
    ObservableField<String> getError();
    ObservableField<String> getResult();
}
