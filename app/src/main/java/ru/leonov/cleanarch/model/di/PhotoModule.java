package ru.leonov.cleanarch.model.di;

import dagger.Module;
import dagger.Provides;

import ru.leonov.cleanarch.model.interactor.photos.IPhotoInteractor;
import ru.leonov.cleanarch.model.interactor.photos.PhotoInteractor;
import ru.leonov.cleanarch.model.repository.IPhotoRepository;
import ru.leonov.cleanarch.presenter.IPhotoPresenter;
import ru.leonov.cleanarch.presenter.IViewPhotos;
import ru.leonov.cleanarch.presenter.PhotoPresenter;

@Module()
public class PhotoModule {

    // в простом случае мы можем использовать поле для хранения зависимости
    private final IViewPhotos viewPhotos;

    // конструктор с параметрами означает, что нам придётся создавать модуль самостоятельно
    public PhotoModule(IViewPhotos viewPhotos) {
        this.viewPhotos = viewPhotos;
    }

    // описываем создание презентера
    // view мы передали в конструктор модуля
    // создание интерактора описали в соседнем методе
    @Provides
    IPhotoPresenter provideUserActivityPresenter(IPhotoInteractor photoInteractor) {
        return new PhotoPresenter(viewPhotos, photoInteractor); //Schedulers.io(), AndroidSchedulers.mainThread()
    }

    // описываем создание интерактора
    // зависимость от UserRepository приходит из родительского компонента
    // создание репозитория описано в AppModule
    @Provides
    IPhotoInteractor provideUserInteractor(IPhotoRepository photoRepository) {
        return new PhotoInteractor(photoRepository);
    }
}