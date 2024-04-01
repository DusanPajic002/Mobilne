package com.example.domaci1.model

data class Cat(
    val name: String,
    val alternativeNames: List<String>,
    val description: String,
    val personalityTraits: List<String>
){
    fun isValid(): Boolean {
        return name.isNotEmpty() && alternativeNames.isNotEmpty() && description.isNotEmpty() && personalityTraits.isNotEmpty()
    }
}