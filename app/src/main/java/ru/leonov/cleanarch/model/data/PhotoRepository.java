package ru.leonov.cleanarch.model.data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Response;
import ru.leonov.cleanarch.model.data.Model.ApiGetPhotos;
import ru.leonov.cleanarch.model.data.Model.ApiPhoto;
import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.network.LoadPhotoHelper;
import ru.leonov.cleanarch.model.network.RequestHelper;
import ru.leonov.cleanarch.model.repository.IPhotoRepository;

public class PhotoRepository implements IPhotoRepository {
    private static final String PHOTO_SIZE = "z";
    private final PhotosMapper mapper;

    public PhotoRepository() {
        mapper = new PhotosMapper();
    }

    public List<PhotoContainer> getPhotos(String searchText, int perPage, int page) throws IOException {

        Response<ApiGetPhotos> response = searchText.equals("")?
                RequestHelper
                        .getJsonPlaceholderApiService()
                        .getResentPhotos(perPage, page)
                        .execute()
                : RequestHelper
                        .getJsonPlaceholderApiService()
                        .searchPhotos(searchText, perPage, page)
                        .execute();

        return mapper.mapList(Objects.requireNonNull(response.body()));
    }

    private class PhotosMapper {
        List<PhotoContainer> mapList(ApiGetPhotos api) {
            List<PhotoContainer> list;
            if (api.getPhotos() != null) {
                list = new ArrayList<>(api.getPhotos().getPhotoList().size());

                for (ApiPhoto apiPhoto : api.getPhotos().getPhotoList()) {
                    list.add(map(apiPhoto));
                }
            }
            else {
                list = new ArrayList<>();
            }

            return list;

        }

        private PhotoContainer map(ApiPhoto apiPhoto) {
            return new PhotoContainer(
                    apiPhoto.getOwner(),
                    apiPhoto.getTitle(),
                    compileUrl(apiPhoto));
        }

        private String compileUrl(ApiPhoto apiPhoto) {
            return LoadPhotoHelper.getPhotoPath(apiPhoto.getFarm(), apiPhoto.getServer(), apiPhoto.getId(), apiPhoto.getSecret(), PHOTO_SIZE);
        }
    }
}
