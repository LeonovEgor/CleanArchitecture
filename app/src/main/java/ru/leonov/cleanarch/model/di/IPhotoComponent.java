//package ru.leonov.cleanarch.model.di;
//
//import dagger.Subcomponent;
//import ru.leonov.cleanarch.view.MainActivity;
//
//// компонент это интерфейс, его реализацию сгенерирует даггер при сборке
//// в данном случае Subcomponent наследует зависимости родительского компонента
//@Subcomponent(modules = PhotoModule.class)
//public interface IPhotoComponent {
//
//    // этот метод требуется указать, чтобы внедрять зависимость в конкретный класс
//    // в данном случае - UserActivity
//    void inject(MainActivity mainActivity);
//
//    // описываем билдер компонента
//    @Subcomponent.Builder
//    interface Builder {
//        // так как PhotoModule не может быть создан автоматически,
//        // мы явно указываем его как зависимость и создаем самостоятельно
//        Builder setModule(PhotoModule photoModule);
//        IPhotoComponent build();
//    }
//}
