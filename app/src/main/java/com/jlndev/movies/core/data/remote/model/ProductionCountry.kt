package com.jlndev.movies.core.data.remote.model


import com.google.gson.annotations.SerializedName

class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso31661: String,
    @SerializedName("name")
    val name: String
)