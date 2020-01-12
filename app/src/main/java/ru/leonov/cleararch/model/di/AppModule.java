package ru.leonov.cleararch.model.di;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.leonov.cleararch.model.data.IPhotoDataSource;
import ru.leonov.cleararch.model.data.PhotoDataSource;
import ru.leonov.cleararch.model.data.PhotoRepository;
import ru.leonov.cleararch.model.network.IJsonPlaceHolderApiService;
import ru.leonov.cleararch.model.network.RequestHelper;
import ru.leonov.cleararch.model.repository.IPhotoRepository;

/**
 * Модуль главного компонента
 * описывает, каким образом создаются зависимости данного компонента
 */
@Module()
public class AppModule {
    /**
     * Специальные методы, помеченные аннотацией @Provides
     * сообщают даггеру, как создавать требуемые зависимости
     * данный метод описывает создание зависимости JsonPlaceHolderApi
     * аннотация @Singleton означает, что будет создан только один объект,
     * сколько бы раз не внедрялась зависимость
     */
    @Provides
    @Singleton
    IJsonPlaceHolderApiService provideJsonPlaceHolderApi() {
        return RequestHelper.getJsonPlaceholderApiService();
    }

    @Provides
    @Singleton
    IPhotoDataSource providePhotoDadaSource() {
        return new PhotoDataSource();
    }

    /**
     * Данный метод описывает создание репозитория
     * он требует зависимость JsonPlaceHolderApi -- как её создавать даггер знает --
     * мы описали соответствующий метод выше.
     * теперь даггер будет знать, как создавать UserRepository для этого и дочерних компонентов
     */
    @Provides
    @Singleton
    IPhotoRepository providePhotoRepository(IPhotoDataSource photoDadaSource) {
        return new PhotoRepository(photoDadaSource);
    }

}
