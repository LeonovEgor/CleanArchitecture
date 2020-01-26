package ru.leonov.cleanarch.model.data.photo;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PositionalDataSource;

import java.io.IOException;
import java.util.List;

import ru.leonov.cleanarch.model.entities.PhotoContainer;
import ru.leonov.cleanarch.model.repository.IPhotoRepository;

public class PhotoPositionalDataSource extends PositionalDataSource<PhotoContainer> {
    private static final String TAG = PhotoPositionalDataSource.class.getSimpleName();

    private final IPhotoRepository photoRepository;
    private String searchString = "";

    public PhotoPositionalDataSource(IPhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams params, @NonNull LoadInitialCallback<PhotoContainer> callback) {
        Log.d(TAG, "loadInitial, requestedStartPosition = " + params.requestedStartPosition +
                ", requestedLoadSize = " + params.requestedLoadSize);
        List<PhotoContainer> result;
        try {
            result = photoRepository.getPhotos(searchString, params.requestedLoadSize, params.requestedStartPosition);
            int realPosition = params.requestedStartPosition;
            callback.onResult(result, realPosition, 1000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadRange(@NonNull LoadRangeParams params, @NonNull LoadRangeCallback<PhotoContainer> callback) {
        Log.d(TAG, "loadRange, startPosition = " + params.startPosition + ", loadSize = " + params.loadSize);
        List<PhotoContainer> result = null;
        try {
            result = photoRepository.getPhotos(searchString, params.loadSize, params.startPosition);
            callback.onResult(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setSearchString(String str) {
        searchString = str;
    }
}
