package ru.leonov.cleararch.ui.main;

public interface IMainPresenter {

    interface PresenterView {
        void showRate();
        void showRunCounter(int count);
    }

    void onStart();
    void onStop();

    //void onShowRate(boolean isShow);
}
