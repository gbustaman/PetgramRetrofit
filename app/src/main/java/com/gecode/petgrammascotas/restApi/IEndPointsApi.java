package com.gecode.petgrammascotas.restApi;

import com.gecode.petgrammascotas.restApi.model.MascotasResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gregorybr on 22-01-17.
 */

public interface IEndPointsApi {
    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotasResponse> getRecentMedia();

    @GET(ConstantesRestApi.URL_SEARCH_USER_BY_NAME)
    Call<MascotasResponse> getUserByName(@Query("q") String userName);

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER_ID)
    Call<MascotasResponse> getRecentMedia(@Path("user-id") Long userId);

    @GET(ConstantesRestApi.URL_GET_FOLLOWED_BY)
    Call<MascotasResponse> getFollowedBy();
}
