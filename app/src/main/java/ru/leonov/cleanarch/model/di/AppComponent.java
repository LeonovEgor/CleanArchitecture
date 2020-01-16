//package ru.leonov.cleanarch.model.di;
//
//import javax.inject.Singleton;
//
//import dagger.Component;
//
///**
// * Главный компонент приложения
// * является родительским для остальных
// * это значит, что зависимости будут передаваться дочерним компонентам
// * Singleton означает область видимости - единственную на всё приложение
// * В аннотации Component перечисляются модули, с которыми работает компонент
// * Компонент хранит зависимости, модуль - создает
// */
//@Singleton
//@Component(modules = { AppModule.class})
//public interface AppComponent {
//
//    // здесь явно указывается, какие дочерние компоненты будут у главного компонента
//    // в данном случае только один IPhotoComponent
//    IPhotoComponent.Builder getUserComponent();
//}
