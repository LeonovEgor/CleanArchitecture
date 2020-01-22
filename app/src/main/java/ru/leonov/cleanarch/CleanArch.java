package ru.leonov.cleanarch;

import android.app.Application;

import ru.leonov.cleanarch.model.data.Storage;
import ru.leonov.cleanarch.model.data.StorageRepository;

import ru.leonov.cleanarch.model.interactor.counter.IRunCounter;
import ru.leonov.cleanarch.model.interactor.counter.RunCounter;
import ru.leonov.cleanarch.model.interactor.main.IRatingLogic;
import ru.leonov.cleanarch.model.interactor.main.RatingLogic;
import ru.leonov.cleanarch.view.Navigator.INavigator;
import ru.leonov.cleanarch.view.Navigator.MainNavigator;

public class CleanArch extends Application  {

    private IRunCounter runCounter;
    private IRatingLogic ratingLogic;
    private MainNavigator navigator;

    public IRunCounter getRunCounter() {return runCounter;}
    public IRatingLogic getRatingLogic() {return ratingLogic;}
    public INavigator getNavigator() {return navigator;}

    private static CleanArch cleanArch;

    public static CleanArch getInstance() {
        return cleanArch;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        cleanArch = this;

        processAppStart();
    }

    private void processAppStart() {
        counterStart();
        ratingStart();
        initNavigator();
    }

    private void counterStart() {
        StorageRepository storageRepository = new StorageRepository(this, new Storage());
        runCounter = new RunCounter(storageRepository);
        runCounter.incrementRun();
    }

    private void ratingStart() {
        ratingLogic = new RatingLogic(runCounter);
    }

    private void initNavigator() {
        navigator = new MainNavigator();
    }
}
