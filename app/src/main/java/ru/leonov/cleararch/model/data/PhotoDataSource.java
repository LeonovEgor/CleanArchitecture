package ru.leonov.cleararch.model.data;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import ru.leonov.cleararch.model.data.Model.ApiGetPhotos;
import ru.leonov.cleararch.model.data.Model.ApiPhoto;
import ru.leonov.cleararch.model.entities.PhotoContainer;
import ru.leonov.cleararch.model.network.LoadPhotoHelper;
import ru.leonov.cleararch.model.network.RequestHelper;

public class PhotoDataSource implements IPhotoDadaSource {
    private static final String PHOTO_SIZE = "z";

    @Override
    public Observable<List<PhotoContainer>> getPhotos(String search) {
        return search.equals("") ?
                getResentPhotos():
                searchPhotos(search);
    }

    private Observable<List<PhotoContainer>> getResentPhotos() {
        return RequestHelper.getJsonPlaceholderApiService().getResentPhotos()
                .map(new Function<ApiGetPhotos, List<PhotoContainer>>() {
                    @Override
                    public List<PhotoContainer> apply(ApiGetPhotos apiGetPhotos) throws Exception {
                        List<PhotoContainer> list;
                        if (apiGetPhotos.getPhotos() != null ) {
                            list = new ArrayList<>(apiGetPhotos.getPhotos().getPhotoList().size());

                            for (ApiPhoto apiPhoto : apiGetPhotos.getPhotos().getPhotoList()) {
                                list.add(new PhotoContainer(
                                        apiPhoto.getOwner(),
                                        apiPhoto.getTitle(),
                                        compileUrl(apiPhoto)));
                            }
                        }
                        else {
                            list = new ArrayList<>();
                        }

                        return list;
                    }
                });
    }

    private Observable<List<PhotoContainer>> searchPhotos(String search) {
        return RequestHelper.getJsonPlaceholderApiService().searchPhotos(search)
                .map(new Function<ApiGetPhotos, List<PhotoContainer>>() {
                    @Override
                    public List<PhotoContainer> apply(ApiGetPhotos apiGetPhotos) throws Exception {
                        List<PhotoContainer> list;
                        if (apiGetPhotos.getPhotos() != null) {
                            list = new ArrayList<>(apiGetPhotos.getPhotos().getPhotoList().size());

                            for (ApiPhoto apiPhoto : apiGetPhotos.getPhotos().getPhotoList()) {
                                list.add(new PhotoContainer(
                                        apiPhoto.getOwner(),
                                        apiPhoto.getTitle(),
                                        compileUrl(apiPhoto)));
                            }
                        }
                        else {
                            list = new ArrayList<>();
                        }

                        return list;
                    }
                });
    }

    private String compileUrl(ApiPhoto apiPhoto) {
        return LoadPhotoHelper.getPhotoPath(apiPhoto.getFarm(), apiPhoto.getServer(), apiPhoto.getId(), apiPhoto.getSecret(), PHOTO_SIZE);
    }
}