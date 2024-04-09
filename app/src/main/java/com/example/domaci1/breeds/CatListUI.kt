package com.example.domaci1.breeds

data class CatListUI(
    val id: String,
    val name: String,
    val alt_names: String = "",
    val description: String,
    val temperament: List<String>,
)