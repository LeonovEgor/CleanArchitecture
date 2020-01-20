//package ru.leonov.cleanarch.model.di;

//import dagger.Module;
//import dagger.Provides;
//
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.schedulers.Schedulers;
//import ru.leonov.cleanarch.model.interactor.photos.IPhotoInteractor;
//import ru.leonov.cleanarch.model.interactor.photos.PhotoInteractor;
//import ru.leonov.cleanarch.model.repository.IPhotoRepository;
//import ru.leonov.cleanarch.viewmodel.PhotoViewModel;

//@Module()
//public class PhotoModule {
//
//    // описываем создание презентера
//    // view мы передали в конструктор модуля
//    // создание интерактора описали в соседнем методе
//    @Provides
//    PhotoViewModel provideMainActivityViewModel(IPhotoInteractor photoInteractor) {
//        return new PhotoViewModel(Schedulers.io(), AndroidSchedulers.mainThread(), photoInteractor);
//    }
//
//    // описываем создание интерактора
//    // зависимость от UserRepository приходит из родительского компонента
//    // создание репозитория описано в AppModule
//    @Provides
//    IPhotoInteractor providePhotoInteractor(IPhotoRepository photoRepository) {
//        return new PhotoInteractor(photoRepository);
//    }
//}