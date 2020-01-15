package ru.leonov.cleararch;

import android.app.Application;

import ru.leonov.cleararch.model.data.Storage;
import ru.leonov.cleararch.model.data.StorageRepository;
import ru.leonov.cleararch.model.di.AppComponent;
import ru.leonov.cleararch.model.di.AppComponentProvider;
import ru.leonov.cleararch.model.di.AppModule;

import ru.leonov.cleararch.model.di.DaggerAppComponent;
import ru.leonov.cleararch.model.interactor.counter.IRunCounter;
import ru.leonov.cleararch.model.interactor.counter.RunCounter;
import ru.leonov.cleararch.model.interactor.main.IRatingLogic;
import ru.leonov.cleararch.model.interactor.main.RatingLogic;

public class ClearArch extends Application implements AppComponentProvider {

    private IRunCounter runCounter;
    private IRatingLogic ratingLogic;

    public IRunCounter getRunCounter() {return runCounter;}
    public IRatingLogic getRatingLogic() {return ratingLogic;}

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();

        processAppStart();
    }

    private void processAppStart() {
        StorageRepository storageRepository = new StorageRepository(this, new Storage());
        runCounter = new RunCounter(storageRepository);
        runCounter.incrementRun();

        ratingLogic = new RatingLogic(runCounter);
    }

    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
