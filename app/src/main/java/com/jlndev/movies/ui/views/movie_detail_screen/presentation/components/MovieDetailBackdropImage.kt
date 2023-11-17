package com.jlndev.movies.ui.views.movie_detail_screen.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlndev.movies.ui.presentation.components.common.AsyncImageUrl

@Composable
fun MovieDetailBackdropImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        AsyncImageUrl(
            imageUrl = imageUrl,
            contentScale = ContentScale.Crop,
            modifier =  Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun MovieDetailBackdropImagePreview() {
    MovieDetailBackdropImage(
        imageUrl = "",
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}