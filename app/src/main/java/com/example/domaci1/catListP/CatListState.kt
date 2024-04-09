package com.example.domaci1.catListP

import com.example.domaci1.networking.breeds.BreedUiModel

data class CatListState(
    val fetching: Boolean = false,
    val cats: List<BreedUiModel> = emptyList(),
    val error: ListError? = null
) {
    sealed class ListError {
        data class ListUpdateFailed(val cause: Throwable? = null) : ListError()
    }
}
