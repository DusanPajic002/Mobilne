package com.example.domaci1.repository

import com.example.domaci1.model.Cat

class Repository {
    private var cats = DataFile.toMutableList()
    fun allCats() : List<Cat> = cats
}