package ru.leonov.cleararch.utils.logger;


public interface ILogger {

    void logException(String tag, String message, Throwable throwable);

    void logDebug(String tag, String message);
}
