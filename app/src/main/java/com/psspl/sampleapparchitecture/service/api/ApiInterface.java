package com.psspl.sampleapparchitecture.service.api;

import com.psspl.sampleapparchitecture.service.model.Profile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by samir on 2/24/2018.
 */

interface ApiInterface {
    String HTTPS_API_BASE_URL = "https://reqres.in/";

    @GET("api/users")
    Call<Profile> getProfileList(@Query("page") int i);

}
