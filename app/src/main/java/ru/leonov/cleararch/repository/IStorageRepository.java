package ru.leonov.cleararch.repository;

import ru.leonov.cleararch.domain.Settings;

public interface IStorageRepository {
    Settings getSettings();
    void setSettings(Settings settings);
}
