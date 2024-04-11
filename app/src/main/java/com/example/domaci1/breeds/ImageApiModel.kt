package com.example.domaci1.breeds

import kotlinx.serialization.Serializable

@Serializable
data class ImageApiModel (
    val id: String,
    val url: String
)