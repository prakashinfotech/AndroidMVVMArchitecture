package com.psspl.sampleapparchitecture.service.api;

import android.arch.lifecycle.MutableLiveData;

import com.psspl.sampleapparchitecture.service.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by samir on 2/24/2018.
 */

public class ApiClient {
    private static ApiClient apiClient;
    private ApiInterface apiInterface;

    private ApiClient() {
        //TODO this gitHubService instance will be injected using Dagger in part #2 ...
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.HTTPS_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiInterface = retrofit.create(ApiInterface.class);
    }

    public synchronized static ApiClient getInstance() {
        //TODO No need to implement this singleton in Part #2 since Dagger will handle it ...
        if (apiClient == null) {
            if (apiClient == null) {
                apiClient = new ApiClient();
            }
        }
        return apiClient;
    }

    public MutableLiveData<Profile> getProfileList(final MutableLiveData<Profile> projectListObservable, int i) {

        apiInterface.getProfileList(i).enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                projectListObservable.postValue(response.body());
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                // TODO better error handling in part #2 ...
                projectListObservable.postValue(null);
            }
        });

        return projectListObservable;
    }

}
