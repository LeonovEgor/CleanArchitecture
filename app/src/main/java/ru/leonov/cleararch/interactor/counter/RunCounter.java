package ru.leonov.cleararch.interactor.counter;

import ru.leonov.cleararch.data.StorageRepository;
import ru.leonov.cleararch.domain.Settings;
import ru.leonov.cleararch.repository.IStorageRepository;

public class RunCounter implements IRunCounter{
    private IStorageRepository repository;

    public RunCounter(StorageRepository repository) {
        this.repository = repository;
    }

    @Override
    public int getRunCount() {
        return repository.getSettings().getRunCounter();
    }

    @Override
    public void incrementRun() {
        Settings settings = repository.getSettings();
        settings.setRunCounter(settings.getRunCounter() + 1);

        repository.setSettings(settings);
    }
}
