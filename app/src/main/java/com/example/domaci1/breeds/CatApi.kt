package com.example.domaci1.breeds

import retrofit2.http.GET
import retrofit2.http.Path

interface CatApi {

    @GET("breeds")
    suspend fun getAllCats(): List<CatApiModel>
    @GET("breeds/{breedId}")
    suspend fun getCat(@Path("breedId") catId: String): CatApiModel
    @GET("images/{imageId}")
    suspend fun get_image_id(@Path("imageId") imageId: String): ImageApiModel

}