package ru.leonov.cleanarch.model.repository;

import ru.leonov.cleanarch.model.entities.Settings;

public interface IStorageRepository {
    Settings getSettings();
    void setSettings(Settings settings);
}
