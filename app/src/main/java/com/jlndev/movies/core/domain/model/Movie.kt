package com.jlndev.movies.core.domain.model

class Movie (
    val id: Int,
    val title: String,
    val voteAverage: Double = 0.0,
    val imageUrl: String = ""
)