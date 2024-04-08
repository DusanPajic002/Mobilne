package com.example.domaci1.repository

import com.example.domaci1.domain.Cat
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlin.time.Duration.Companion.seconds

object Repository {

    private val cats = MutableStateFlow(listOf<Cat>())
    fun allCats(): List<Cat> = cats.value
    fun observeCats(): Flow<List<Cat>> = cats.asStateFlow()
    fun getById(id: String): Cat? {
        return cats.value.find { it.id == id }
    }
    suspend fun fetchCats() {
        delay(2.seconds)
        cats.update { DataFile.toMutableList() }
    }
    suspend fun fetchCatDetails(catId: String) {
        delay(1.seconds)
    }
    fun observeCatsDetails(catId: String): Flow<Cat?> {
        return observeCats()
            .map { cats -> cats.find { it.id == catId } }
            .distinctUntilChanged()
    }

}