package ru.leonov.cleanarch.model.interactor.counter;

import ru.leonov.cleanarch.model.data.storage.StorageRepository;
import ru.leonov.cleanarch.model.entities.Settings;
import ru.leonov.cleanarch.model.repository.IStorageRepository;

public class RunCounter implements IRunCounter{
    private final IStorageRepository repository;

    public RunCounter(IStorageRepository repository) {
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
