package ru.leonov.cleanarch.presenter;


import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.view.navigator.INavigator;

public class PhotosPresenter implements IPhotosPresenter {
    private final INavigator navigator;

    public PhotosPresenter(INavigator navigator) {
        this.navigator = navigator;
    }

    @Override
    public void showDetails(PhotoContainer photoContainer) {
        navigator.gotoPhotosDetailsScreen(photoContainer);
    }

    @Override
    public void onStart() {
        navigator.gotoPhotosListScreen();
    }
}
