package ru.leonov.cleanarch.model.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.leonov.cleanarch.model.data.model.person.ApiGetPerson;
import ru.leonov.cleanarch.model.data.model.photos.ApiGetPhotos;

public interface IJsonPlaceHolderApiService {

    @GET("?method=flickr.photos.getRecent&api_key=e35a0796dc99d301d3867ccd3b8b495b&format=json&nojsoncallback=1")
    Call<ApiGetPhotos> getResentPhotos();

    @GET("?method=flickr.photos.getRecent&api_key=e35a0796dc99d301d3867ccd3b8b495b&format=json&nojsoncallback=1")
    Call<ApiGetPhotos> getResentPhotos(@Query("per_page") int perPage, @Query("page") int page);

    // https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=e35a0796dc99d301d3867ccd3b8b495b&text=cats&format=json&nojsoncallback=1
    @GET("?method=flickr.photos.search&api_key=e35a0796dc99d301d3867ccd3b8b495b&format=json&nojsoncallback=1")
    Call<ApiGetPhotos> searchPhotos(@Query("text") String text);

    @GET("?method=flickr.photos.search&api_key=e35a0796dc99d301d3867ccd3b8b495b&format=json&nojsoncallback=1")
    Call<ApiGetPhotos> searchPhotos(@Query("text") String text, @Query("per_page") int perPage, @Query("page") int page);

    @GET("?method=flickr.people.getInfo&api_key=e35a0796dc99d301d3867ccd3b8b495b&format=json&nojsoncallback=1")
    Call<ApiGetPerson> getPerson(@Query("user_id") String userId);

}
