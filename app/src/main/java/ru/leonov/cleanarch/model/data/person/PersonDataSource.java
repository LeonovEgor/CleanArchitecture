package ru.leonov.cleanarch.model.data.person;

import ru.leonov.cleanarch.model.repository.IResponseCallback;

public class PersonDataSource {
    private final PersonRepository repository;

    public PersonDataSource(PersonRepository repository) {
        this.repository = repository;
    }

    public void getPerson(String personId, IResponseCallback callback) {
        repository.getPerson(personId, callback);
    }
}
