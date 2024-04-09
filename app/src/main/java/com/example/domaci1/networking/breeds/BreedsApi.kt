package com.example.domaci1.networking.breeds

import retrofit2.http.GET
import retrofit2.http.Path

public interface BreedsApi {

    @GET("breeds")
    suspend fun getAllBreeds(): List<BreedApiModel>

    @GET("breeds/{breedId}")
    suspend fun getBreed(@Path("breedId") breedId: String): BreedApiModel

}