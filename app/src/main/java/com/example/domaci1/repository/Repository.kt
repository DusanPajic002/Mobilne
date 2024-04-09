package com.example.domaci1.repository

import com.example.domaci1.breeds.CatApi
import com.example.domaci1.breeds.CatApiModel
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

}