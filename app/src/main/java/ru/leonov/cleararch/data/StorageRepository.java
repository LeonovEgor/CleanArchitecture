package ru.leonov.cleararch.data;

import android.content.Context;

import ru.leonov.cleararch.domain.Settings;
import ru.leonov.cleararch.repository.IStorageRepository;

public class StorageRepository implements IStorageRepository {

    private final Storage storage;
    private final Context context;

    public StorageRepository(Context context, Storage storage) {
        this.context = context;
        this.storage = storage;
    }

    @Override
    public Settings getSettings() {
        return storage.getSettings(context);
    }

    @Override
    public void setSettings(Settings settings) {
        storage.setSettings(context, settings);
    }
}
