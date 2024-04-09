package com.example.domaci1.catProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domaci1.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class CatProfileViewModel(
    private val catId: String,
    private val repository: Repository = Repository,
) : ViewModel() {

    private val _state = MutableStateFlow(CatProfileState(catId = catId))
    val state = _state.asStateFlow()
    private fun setState(reducer: CatProfileState.() -> CatProfileState) =
        _state.getAndUpdate(reducer)

    init {
        fetchCat()
    }

    private fun fetchCats() {
        viewModelScope.launch {
            setState { copy(fetching = true) }
            try {
                val cats = withContext(Dispatchers.IO) {
                    repository.fetchAllBreeds().map { it.asBreedUiModel() }
                }
                setState { copy(cats = cats ) }
            } catch (error: Exception) {
                // TODO Handle error
            } finally {
                setState { copy(fetching = false) }
            }
        }
    }

  /*  private fun observeCatProfile() {
        viewModelScope.launch {
            repository.observeCatsDetails(catId = catId)
                .filterNotNull()
                .collect {
                    setState { copy(data = it) }
                }
        }
    }*/


}