package ru.leonov.cleanarch;

import android.app.Application;

import ru.leonov.cleanarch.model.data.Storage;
import ru.leonov.cleanarch.model.data.StorageRepository;
import ru.leonov.cleanarch.model.di.AppComponent;
import ru.leonov.cleanarch.model.di.AppComponentProvider;
import ru.leonov.cleanarch.model.di.AppModule;

import ru.leonov.cleanarch.model.di.DaggerAppComponent;
import ru.leonov.cleanarch.model.interactor.counter.IRunCounter;
import ru.leonov.cleanarch.model.interactor.counter.RunCounter;
import ru.leonov.cleanarch.model.interactor.main.IRatingLogic;
import ru.leonov.cleanarch.model.interactor.main.RatingLogic;

public class CleanArch extends Application implements AppComponentProvider {

    private IRunCounter runCounter;
    private IRatingLogic ratingLogic;

    public IRunCounter getRunCounter() {return runCounter;}
    public IRatingLogic getRatingLogic() {return ratingLogic;}

    private AppComponent appComponent;

    private static CleanArch cleanArch;

    public static CleanArch getInstance() {
        return cleanArch;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        cleanArch = this;

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
