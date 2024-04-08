package com.example.domaci1.catProfile

import com.example.domaci1.domain.Cat

data class CatProfileState (
    val catId: String,
    val fetching: Boolean = false,
    val data: Cat? = null,
    val error: DetailsError? = null,
) {
    sealed class DetailsError {
        data class DataUpdateFailed(val cause: Throwable? = null) : DetailsError()
    }
}
