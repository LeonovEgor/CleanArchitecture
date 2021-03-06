package ru.leonov.cleanarch.presenter;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import ru.leonov.cleanarch.model.PhotoViewState;
import ru.leonov.cleanarch.model.interactor.photos.IPhotoInteractor;

public class PhotoPresenter  implements IPhotoPresenter{
    private final IViewPhotos viewPhotos;
    private Disposable disposable;
    private IPhotoInteractor interactor;

    public PhotoPresenter(IViewPhotos viewPhotos, final IPhotoInteractor interactor) {
        this.viewPhotos = viewPhotos;
        this.interactor = interactor;

        viewPhotos.userActionIntent()
                .flatMap(new Function<String, ObservableSource<PhotoViewState>>() {
                    @Override
                    public ObservableSource<PhotoViewState> apply(String search) {
                        return interactor.getPhotos(search)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new PhotosObserver());
    }

    @Override
    public void onStart() {
        viewPhotos.onStartIntent()
                .flatMap(new Function<String, ObservableSource<PhotoViewState>>() {
                    @Override
                    public ObservableSource<PhotoViewState> apply(String search) {
                        return interactor.getPhotos(search)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread());
                    }
                })
                .subscribe(new PhotosObserver());
    }

    @Override
    public void onStop() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    private class PhotosObserver implements Observer<PhotoViewState> {

        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(PhotoViewState photoViewState) {
            // при получении нового состояния просим View отобразить его
            viewPhotos.render(photoViewState);
        }

        @Override
        public void onError(Throwable e) {
            viewPhotos.render(new PhotoViewState(false, e, null));
        }

        @Override
        public void onComplete() {
            // empty
        }
    }
}
