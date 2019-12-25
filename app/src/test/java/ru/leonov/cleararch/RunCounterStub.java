package ru.leonov.cleararch;

import ru.leonov.cleararch.interactor.counter.IRunCounter;

public class RunCounterStub implements IRunCounter {
    private int runCount = 0;
    public void setRunCount(int runCount) {
        this.runCount = runCount;
    }
    @Override
    public int getRunCount() {
        return runCount;
    }

    @Override
    public void IncrementRun() {
        runCount++;
    }
}
