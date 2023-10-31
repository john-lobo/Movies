package com.jlndev.movies.core.data.remote.response


import com.google.gson.annotations.SerializedName
import com.jlndev.movies.core.data.remote.model.SearchResult

class SearchResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<SearchResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)