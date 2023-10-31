package com.jlndev.movies.core.data.remote.model


import com.google.gson.annotations.SerializedName

class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)