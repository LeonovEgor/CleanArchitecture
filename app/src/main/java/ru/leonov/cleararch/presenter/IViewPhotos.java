package ru.leonov.cleararch.presenter;

import io.reactivex.Observable;
import ru.leonov.cleararch.model.PhotoViewState;

public interface IViewPhotos {
    // метод для отображения Модели (ViewState)
    // в общем случае это единственный метод для изменения состояния IViewPhotos
    void render(PhotoViewState viewState);

    // IViewPhotos передает в презентер пользовательские действия - интенты
    // в данном примере используется RxJava,
    // но IViewPhotos может вызывать методы презентера и обычным способом
    Observable<Boolean> userActionIntent();

    //Observable<Boolean> onStartIntent();
}
