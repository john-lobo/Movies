package com.jlndev.movies.ui.views.movie_favorite_screen.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jlndev.movies.R
import com.jlndev.movies.core.domain.model.Movie
import com.jlndev.movies.ui.theme.black
import com.jlndev.movies.ui.theme.white

@Composable
fun MovieFavoriteItem(
    modifier: Modifier = Modifier,
    movie: Movie,
    onClick: (id: Int) -> Unit
) {
    Card (
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                onClick(movie.id)
            }
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .background(black),
            verticalArrangement =  Arrangement.spacedBy(8.dp)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.imageUrl)
                        .crossfade(true)
                        .error(R.drawable.ic_error_image)
                        .placeholder(R.drawable.ic_placeholder)
                        .build(),
                    contentDescription = null
                )
            }
            Text(
                text = movie.title,
                maxLines = 1,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = white,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun MovieFavoriteItemPreview() {
    MovieFavoriteItem(
        movie = Movie(
            id = 1,
            title = "Homem Aranha",
            voteAverage = 7.89,
            imageUrl = ""
        ),
        onClick =  {})
}