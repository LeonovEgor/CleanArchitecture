package ru.leonov.cleararch.interactor.main;

import ru.leonov.cleararch.interactor.counter.IRunCounter;

public class RatingLogic implements IRatingLogic{
    private static final int FIRST_SHOW = 2;
    private static final int NEXT_SHOW = 4;

    private IRunCounter counter;

    public RatingLogic(IRunCounter counter) {
        this.counter = counter;
    }

    public boolean isShouldShowRatingRequest() {
        return doCalc(counter.getRunCount());
    }

    private boolean doCalc(int runCount) {
        boolean res = false;
        if (runCount == FIRST_SHOW) res = true;
        else if ((runCount-FIRST_SHOW) % NEXT_SHOW == 0) res = true;

        return res;
    }
}
