package ru.leonov.cleanarch;

import org.junit.Test;

import ru.leonov.cleanarch.model.interactor.main.IRatingLogic;
import ru.leonov.cleanarch.model.interactor.main.RatingLogic;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class RunCounterTest {
    @Test
    public void run2TimesTest() {
        int runCount = 2;
        RunCounterStub runCounterStub = new RunCounterStub();
        runCounterStub.setRunCount(runCount);
        IRatingLogic rl = new RatingLogic(runCounterStub);

        assertTrue(rl.isShouldShowRatingRequest());
    }

    @Test
    public void run3TimesTest() {
        int runCount = 3;
        RunCounterStub runCounterStub = new RunCounterStub();
        runCounterStub.setRunCount(runCount);
        IRatingLogic rl = new RatingLogic(runCounterStub);

        assertFalse(rl.isShouldShowRatingRequest());
    }

    @Test
    public void run6TimesTest() {
        int runCount = 6;
        RunCounterStub runCounterStub = new RunCounterStub();
        runCounterStub.setRunCount(runCount);
        IRatingLogic rl = new RatingLogic(runCounterStub);

        assertTrue(rl.isShouldShowRatingRequest());
    }

    @Test
    public void run10TimesTest() {
        int runCount = 10;
        RunCounterStub runCounterStub = new RunCounterStub();
        runCounterStub.setRunCount(runCount);
        IRatingLogic rl = new RatingLogic(runCounterStub);

        assertTrue(rl.isShouldShowRatingRequest());
    }

    @Test
    public void run11TimesTest() {
        int runCount = 11;
        RunCounterStub runCounterStub = new RunCounterStub();
        runCounterStub.setRunCount(runCount);
        IRatingLogic rl = new RatingLogic(runCounterStub);

        assertFalse(rl.isShouldShowRatingRequest());
    }

}