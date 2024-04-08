package com.example.domaci1.catListP

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domaci1.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class CatListViewModel (
    private val repository: Repository = Repository()
) : ViewModel() {

    private val _state = MutableStateFlow(CatListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatListState.() -> CatListState) = _state.getAndUpdate(reducer)


    init {
        observeCats()
        fetchCats()
    }

    private fun observeCats() {
        viewModelScope.launch {
            repository.observeCats().collect {
                setState { copy(cats = it) }
            }
        }
    }

    private fun fetchCats() {
        viewModelScope.launch {
            setState { copy(fetching = true) }
            try {
                withContext(Dispatchers.IO) {
                    repository.fetchCats()
                }
            } catch (error: IOException) {
                setState { copy(error = CatListState.ListError.ListUpdateFailed(cause = error)) }
            } finally {
                setState { copy(fetching = false) }
            }
        }
    }
}
