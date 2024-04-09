package com.example.domaci1.networking.breeds

data class BreedUiModel(
    val id: String,
    val name: String,
    val alt_names: String = "",
    val description: String,
    val temperament: List<String>,
)