package com.jlndev.movies.ui.views.movie_detail_screen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jlndev.movies.R
import com.jlndev.movies.core.domain.model.MovieDetails

@Composable
fun MovieDetailInfoContent(
    movieDetails: MovieDetails?
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        MovieDetailInfo(

            name = stringResource(R.string.vote_average),
            value = movieDetails?.voteAverage.toString()
        )
        MovieDetailInfo(
            name = stringResource(R.string.duration),
            value = stringResource(id = R.string.duration_minute, movieDetails?.duration.toString())
        )
        MovieDetailInfo(
            name = stringResource(R.string.release_date),
            value = movieDetails?.releaseDate.toString()
        )
    }
}

@Composable
fun MovieDetailInfo(
    name: String,
    value: String
) {
    Column {
        Text(
            text = name,
            color = Color.DarkGray,
            style = MaterialTheme.typography.subtitle2.copy(fontSize = 13.sp, letterSpacing = 1.sp),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Text(
            text = value,
            color = Color.DarkGray,
            style = MaterialTheme.typography.subtitle2.copy(
                fontWeight = FontWeight.SemiBold,
                letterSpacing = 1.sp
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailFieldsPreview() {
    MovieDetailInfoContent(
        movieDetails = MovieDetails(
            id = 1,
            title = "Filme",
            genres = listOf("Comédia", "Aventura"),
            overview = null,
            backdropPathUrl = null,
            releaseDate = null,
            voteAverage = 7.5,
            duration = 90,
            voteCount = 100
        ),
    )
}

