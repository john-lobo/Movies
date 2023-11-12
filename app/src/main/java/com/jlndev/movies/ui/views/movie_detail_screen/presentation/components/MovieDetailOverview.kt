package com.jlndev.movies.ui.views.movie_detail_screen.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jlndev.movies.R
import com.jlndev.movies.ui.theme.white

@Composable
fun MovieDetailOverview(
    overview: String
) {
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 8.dp)
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(
                text = stringResource(R.string.description),
                color = white,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 20.sp
            )
            Text(
                text = overview,
                color = Color.DarkGray,
                fontFamily = FontFamily.SansSerif,
                fontSize = 15.sp,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                overflow = if (expanded) TextOverflow.Clip else TextOverflow.Ellipsis,
                modifier = Modifier.clickable {
                    expanded = !expanded
                }
            )
        }
        IconButton(
            onClick = {
                expanded = !expanded
            }
        ) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                tint = white
            )
        }
    }
}

@Preview (showBackground = true, backgroundColor = 0)
@Composable
fun MovieDetailOverviewPreview() {
    MovieDetailOverview(
        overview = "Filme filme"
    )
}