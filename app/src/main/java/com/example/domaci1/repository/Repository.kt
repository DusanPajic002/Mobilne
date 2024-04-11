package com.example.domaci1.repository

import android.util.Log
import com.example.domaci1.breeds.CatApi
import com.example.domaci1.breeds.CatApiModel
import com.example.domaci1.breeds.ImageApiModel
import rs.edu.raf.rma6.networking.retrofit

object Repository {

    private val catsApi: CatApi = retrofit.create(CatApi::class.java)
    suspend fun fetchAllCats(): List<CatApiModel> {
        val cats = catsApi.getAllCats()
        return cats
    }
    suspend fun fetchCat(catId: String): CatApiModel {
        val cat = catsApi.getCat(catId)
        return cat
    }
    suspend fun fetchCatImages(imageId: String): ImageApiModel {
        Log.d("CATTTA","dsadsadsadsadsadsa1111111")
        return catsApi.get_image_id(imageId)
    }


}