package com.example.domaci1.catProfile

import com.example.domaci1.networking.breeds.BreedUiModel

data class CatProfileState (
    val catId: String,
    val fetching: Boolean = false,
    val data: BreedUiModel? = null,
    val error: DetailsError? = null,
) {
    sealed class DetailsError {
        data class DataUpdateFailed(val cause: Throwable? = null) : DetailsError()
    }
}
