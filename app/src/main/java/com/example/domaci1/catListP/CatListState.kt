package com.example.domaci1.catListP

import com.example.domaci1.breeds.CatListUI

data class CatListState(
    val fetching: Boolean = false,
    val cats: List<CatListUI> = emptyList(),
    val error: ListError? = null,
    val filter: String = "",
    val filteredCats: List<CatListUI> = emptyList(),
) {
    sealed class FilterEvent {
        data object filterClick : FilterEvent()
        data class filterEvent(val textfilt: String) : FilterEvent()
    }
    sealed class ListError {
        data class ListUpdateFailed(val cause: Throwable? = null) : ListError()
    }
}
