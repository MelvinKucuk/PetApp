package com.melvin.flux.dao;

import com.melvin.flux.model.Pet;
import com.melvin.flux.model.Pets;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicePet {

    @GET("findByStatus/")
    @Headers({"Accept: Application/xml"})
    Call<Pets> getAllPets(@Query("status") String query);

    @GET("/{petId}")
    @Headers({"Accept: Application/xml"})
    Call<Pet> getPetById(@Path("petId") String id);
}
