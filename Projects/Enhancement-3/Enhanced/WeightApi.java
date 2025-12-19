package com.example.weighttrackingoml;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WeightApi {

    @GET("weights")
    Call<List<Weight>> getWeights(@Header("Authorization") String authHeader);

    @POST("weights")
    Call<Void> addWeight(
            @Header("Authorization") String authHeader,
            @Body Weight weight
    );

    @DELETE("weights/{id}")
    Call<Void> deleteWeight(
            @Header("Authorization") String authHeader,
            @Path("id") String id);

}
