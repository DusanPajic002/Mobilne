package com.example.domaci1.catListP

import com.example.domaci1.breeds.CatListUI

data class CatListState(
    val fetching: Boolean = false,
    val cats: List<CatListUI> = emptyList(),
    val error: ListError? = null
) {
    sealed class ListError {
        data class ListUpdateFailed(val cause: Throwable? = null) : ListError()
    }
}
