package ru.leonov.cleararch.model.repository;

import ru.leonov.cleararch.model.entities.Settings;

public interface IStorageRepository {
    Settings getSettings();
    void setSettings(Settings settings);
}
