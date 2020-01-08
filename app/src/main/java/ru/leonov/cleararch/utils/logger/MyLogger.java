package ru.leonov.cleararch.utils.logger;

import android.util.Log;

public class MyLogger implements ILogger {
    private ILogger logger = null;

    @Override
    public void logException(String tag, String message, Throwable throwable) {
        Log.w(tag, message, throwable);
    }

    @Override
    public void logDebug(String tag, String message) {
        Log.d(tag, message);
    }
}
