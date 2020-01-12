package ru.leonov.cleararch.model.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.leonov.cleararch.model.data.Model.ApiGetPhotos;

public interface IJsonPlaceholderApiService {

    @GET("?method=flickr.photos.getRecent&api_key=e35a0796dc99d301d3867ccd3b8b495b&format=json&nojsoncallback=1")
    Observable<ApiGetPhotos> getResentPhotos();

    // https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=e35a0796dc99d301d3867ccd3b8b495b&text=cats&format=json&nojsoncallback=1
    @GET("?method=flickr.photos.search&api_key=e35a0796dc99d301d3867ccd3b8b495b&format=json&nojsoncallback=1")
    Observable<ApiGetPhotos> searchPhotos(@Query("text") String text);
}
