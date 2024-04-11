package com.example.domaci1.catProfile

import com.example.domaci1.breeds.CatProfileUI

data class CatProfileState (
    val catId: String,
    val fetching: Boolean = false,
    val cat: CatProfileUI? = null,
    val image: String? = null,
    val error: DetailsError? = null,
) {
    sealed class DetailsError {
        data class DataUpdateFailed(val cause: Throwable? = null) : DetailsError()
    }
}
