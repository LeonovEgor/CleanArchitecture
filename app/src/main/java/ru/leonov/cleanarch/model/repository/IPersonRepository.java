package ru.leonov.cleanarch.model.repository;

public interface IPersonRepository {
    void getPerson(String personId, IResponseCallback callback);
}
