package com.example.domaci1.catListP

import com.example.domaci1.domain.Cat

data class CatListState(
    val fetching: Boolean = false,
    val cats: List<Cat> = emptyList(),
    val error: ListError? = null
) {
    sealed class ListError {
        data class ListUpdateFailed(val cause: Throwable? = null) : ListError()
    }
}
