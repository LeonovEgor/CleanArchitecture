package ru.leonov.cleararch.interactor.counter;

import ru.leonov.cleararch.data.StorageRepository;
import ru.leonov.cleararch.domain.Settings;
import ru.leonov.cleararch.repository.IStorageRepository;

public class RunCounter implements IRunCounter{
    IStorageRepository repository;

    public RunCounter(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getRunCount() {
        return repository.getSettings().runCounter;
    }

    @Override
    public void IncrementRun() {
        Settings settings = repository.getSettings();
        settings.runCounter++;

        repository.setSettings(settings);
    }
}
