package com.example.domaci1.repository

import com.example.domaci1.networking.breeds.BreedApiModel
import com.example.domaci1.networking.breeds.BreedsApi
import rs.edu.raf.rma6.networking.retrofit

object Repository {

    private val breedsApi: BreedsApi = retrofit.create(BreedsApi::class.java)
    suspend fun fetchAllBreeds(): List<BreedApiModel> {
        val breeds = breedsApi.getAllBreeds()
        return breeds
    }
/*
    suspend fun fetchCatDetails(catId: String) {
        delay(1.seconds)
    }
    fun observeCatsDetails(catId: String): Flow<Cat?> {
        return observeCats()
            .map { cats -> cats.find { it.id == catId } }
            .distinctUntilChanged()
    }
*/

}