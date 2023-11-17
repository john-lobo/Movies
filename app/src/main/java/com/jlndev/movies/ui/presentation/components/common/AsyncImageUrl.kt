package com.jlndev.movies.ui.presentation.components.common

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jlndev.movies.R

@Composable
fun AsyncImageUrl(
    modifier: Modifier = Modifier,
    imageUrl: String,
    crossFadeEnable: Boolean = true,
    @DrawableRes errorImage: Int = R.drawable.ic_error_image,
    @DrawableRes placeHolderImage: Int = R.drawable.ic_placeholder,
    contentScale: ContentScale = ContentScale.FillHeight
) {
    AsyncImage(model = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(crossFadeEnable)
        .error(errorImage)
        .placeholder(placeHolderImage)
        .build(),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier)
}