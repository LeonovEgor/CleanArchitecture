package ru.leonov.cleararch.presenter;


import ru.leonov.cleararch.model.interactor.counter.IRunCounter;
import ru.leonov.cleararch.model.interactor.main.IRatingLogic;
import ru.leonov.cleararch.model.utils.logger.ILogger;

public class MainPresenter {
    private static final String LOG_TAG = "MAIN_PRESENTER";

//    private PresenterView presenterView;
//    private IRatingLogic ratingLogic;
//    private IRunCounter runCounter;
//    private ILogger logger;
//
//    public MainPresenter(PresenterView presenterView, IRatingLogic ratingLogic, IRunCounter runCounter, ILogger logger) {
//        this.presenterView = presenterView;
//        this.ratingLogic = ratingLogic;
//        this.runCounter = runCounter;
//        this.logger = logger;
//    }

//    @Override
//    public void onStart() {
//        logger.logDebug(LOG_TAG, "start MainPresenter");
//
//        showCounter();
//        ratingLogic();
//    }
//
//    private void showCounter() {
//        presenterView.showRunCounter(runCounter.getRunCount());
//    }
//
//    private void ratingLogic() {
//        if (ratingLogic.isShouldShowRatingRequest()) {
//            logger.logDebug(LOG_TAG, "Should show rating");
//            presenterView.showRate();
//        } else {
//            logger.logDebug(LOG_TAG, "Shouldn't show rating");
//        }
//    }
//
//    @Override
//    public void onStop() {
//        logger.logDebug(LOG_TAG, "stop MainPresenter");
//    }

}
