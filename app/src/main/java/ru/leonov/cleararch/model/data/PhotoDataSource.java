package ru.leonov.cleararch.model.data;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import ru.leonov.cleararch.model.data.Model.ApiGetResentPhotos;
import ru.leonov.cleararch.model.data.Model.ApiPhoto;
import ru.leonov.cleararch.model.entities.PhotoContainer;
import ru.leonov.cleararch.model.network.LoadPhotoHelper;
import ru.leonov.cleararch.model.network.RequestHelper;

public class PhotoDataSource implements IPhotoDadaSource {
    public static final String PHOTO_SIZE = "z";
//    private final IJsonPlaceholderApiService jsonPlaceHolderApi;

    public PhotoDataSource(/*IJsonPlaceholderApiService jsonPlaceHolderApi*/) {
//        this.jsonPlaceHolderApi = jsonPlaceHolderApi;
    }

    @Override
    public Observable<List<PhotoContainer>> getPhotos() {
        return RequestHelper.getJsonPlaceholderApiService().getResentPhotos()
                .map(new Function<ApiGetResentPhotos, List<PhotoContainer>>() {
                    @Override
                    public List<PhotoContainer> apply(ApiGetResentPhotos apiGetResentPhotos) throws Exception {
                        List<PhotoContainer> list = null;
                        if (apiGetResentPhotos.getPhotos() != null) {
                            list = new ArrayList<>(apiGetResentPhotos.getPhotos().getPhotoList().size());

                            for (ApiPhoto apiPhoto : apiGetResentPhotos.getPhotos().getPhotoList()) {
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