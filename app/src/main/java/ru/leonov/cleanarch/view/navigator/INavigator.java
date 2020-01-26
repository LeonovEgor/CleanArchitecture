package ru.leonov.cleanarch.view.navigator;

import androidx.fragment.app.FragmentActivity;

import ru.leonov.cleanarch.model.entities.PhotoContainer;

public interface INavigator {
    void gotoPhotosListScreen();

    void gotoPhotosDetailsScreen(PhotoContainer photoContainer);

    void attachActivity(FragmentActivity activity);

    void detachActivity();
}

