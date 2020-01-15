package ru.leonov.cleanarch.model.interactor.counter;

import ru.leonov.cleanarch.model.data.StorageRepository;
import ru.leonov.cleanarch.model.entities.Settings;
import ru.leonov.cleanarch.model.repository.IStorageRepository;

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
