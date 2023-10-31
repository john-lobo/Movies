package com.jlndev.movies.core.util

import com.jlndev.movies.BuildConfig

fun String?.toImageUrl() = "${BuildConfig.BASE_URL_IMAGE}$this"