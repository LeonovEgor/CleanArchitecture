package ru.leonov.cleanarch.presenter;


import ru.leonov.cleanarch.view.Navigator.INavigator;

public class PhotosPresenter implements IPhotosPresenter {
    private final INavigator navigator;

    public PhotosPresenter(INavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void showDetails() {
        navigator.getPhotosDetailsScreen();
    }

    @Override
    public void onStart() {
        navigator.getPhotosListScreen();
    }
}
