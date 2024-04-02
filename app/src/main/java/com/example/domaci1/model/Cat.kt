package com.example.domaci1.model

data class Cat(
    val id: String,
    val name: String,
    val alternativeNames: List<String>,
    val description: String,
    val personalityTraits: List<String>,
    val origin: String,
    val lifeSpan: String,
    val size: String,
    val isRare: String,
    val wikiUrl: String
){
    fun isValid(): Boolean {
        return name.isNotEmpty() && alternativeNames.isNotEmpty() && description.isNotEmpty() && personalityTraits.isNotEmpty()
    }
}