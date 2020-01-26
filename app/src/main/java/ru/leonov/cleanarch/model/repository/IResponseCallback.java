package ru.leonov.cleanarch.model.repository;

public interface IResponseCallback {
    void response(String name);
    void responseError(String error);

}
