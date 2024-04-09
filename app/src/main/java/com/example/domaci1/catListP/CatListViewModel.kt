package com.example.domaci1.catListP

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domaci1.breeds.CatApiModel
import com.example.domaci1.breeds.CatListUI
import com.example.domaci1.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CatListViewModel (
    private val repository: Repository = Repository
) : ViewModel() {

    private val _state = MutableStateFlow(CatListState())
    val state = _state.asStateFlow()
    private fun setState(reducer: CatListState.() -> CatListState) = _state.getAndUpdate(reducer)

    init {
        fetchCats()
    }

    private fun fetchCats() {
        viewModelScope.launch {
            setState { copy(fetching = true) }
            try {
                val cats = withContext(Dispatchers.IO) {
                    repository.fetchAllCats().map { it.asCatUiModel() }
                }
                setState { copy(cats = cats ) }
            } catch (error: Exception) {
                // TODO Handle error
            } finally {
                setState { copy(fetching = false) }
            }
        }
    }

    private fun CatApiModel.asCatUiModel() = CatListUI(
        id = this.id,
        name = this.name,
        alt_names = this.alt_names ,
        description = this.description,
        temperament = this.temperament.split(", ")
    )

}
