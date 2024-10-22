package com.example.rest_app_tracks;
import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIRequests {
    @GET("tracks")
    Call<List<Track>> getTracks();

    @POST("tracks")
    Call<Track> AddTrack(@Body Track newTrack);

    @DELETE("tracks/{id}")
    Call<Void> DeleteTrack(@Path("id") String trackId);

    @PUT("tracks")
    Call<Void> UpdateTrack(@Body Track UpdatedTrack);
}
