package ru.leonov.cleanarch.model.data.person;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.leonov.cleanarch.model.data.model.person.ApiGetPerson;
import ru.leonov.cleanarch.model.network.RequestHelper;
import ru.leonov.cleanarch.model.repository.IPersonRepository;
import ru.leonov.cleanarch.model.repository.IResponseCallback;

public class PersonRepository implements IPersonRepository {

    @Override
    public void getPerson(final String personId, final IResponseCallback callback) {
        RequestHelper
                .getJsonPlaceholderApiService()
                .getPerson(personId)
                .enqueue(new Callback<ApiGetPerson>() {
                    @Override
                    public void onResponse(@NonNull Call<ApiGetPerson> call,
                                           @NonNull Response<ApiGetPerson> response) {
                        if (response.body() != null && response.isSuccessful()) {
                            String name = new PersonMapper().map(response.body());
                            callback.response(name);
                        }
                        else {
                            callback.response(personId);
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ApiGetPerson> call, @NonNull Throwable t) {
                        callback.responseError(t.getMessage());
                    }
                });
    }

    private class PersonMapper {
        String map(ApiGetPerson api) {
            return api.person != null ?
                    String.format("%s (%s)",api.person.username.content, api.person.realname.content):
                    "";
        }
    }
}
