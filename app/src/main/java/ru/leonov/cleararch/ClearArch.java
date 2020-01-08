package ru.leonov.cleararch;

import android.app.Application;

import ru.leonov.cleararch.data.Storage;
import ru.leonov.cleararch.data.StorageRepository;
import ru.leonov.cleararch.interactor.counter.IRunCounter;
import ru.leonov.cleararch.interactor.counter.RunCounter;
import ru.leonov.cleararch.interactor.main.IRatingLogic;
import ru.leonov.cleararch.interactor.main.RatingLogic;


public class ClearArch extends Application {

    private IRunCounter runCounter;
    private IRatingLogic ratingLogic;

    public IRunCounter getRunCounter() {return runCounter;}
    public IRatingLogic getRatingLogic() {return ratingLogic;}

    @Override
    public void onCreate() {
        super.onCreate();

        processAppStart();
    }

    private void processAppStart() {
        StorageRepository storageRepository = new StorageRepository(this, new Storage());
        runCounter = new RunCounter(storageRepository);
        runCounter.incrementRun();

        ratingLogic = new RatingLogic(runCounter);
    }

}
