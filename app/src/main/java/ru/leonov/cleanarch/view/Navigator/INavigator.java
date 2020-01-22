package ru.leonov.cleanarch.view.Navigator;

import androidx.fragment.app.FragmentActivity;

public interface INavigator {
    void getPhotosListScreen();

    void getPhotosDetailsScreen();

    void attachActivity(FragmentActivity activity);

    public void detachActivity();
}

