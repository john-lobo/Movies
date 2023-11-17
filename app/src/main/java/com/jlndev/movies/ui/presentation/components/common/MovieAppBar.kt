package com.jlndev.movies.ui.presentation.components.common

import androidx.annotation.StringRes
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.jlndev.movies.ui.theme.black
import com.jlndev.movies.ui.theme.white

@Composable
fun MovieAppBar(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
    textColor: Color = white,
    backgroundColor: Color = black
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(title),
                color = textColor
            )
        },
        backgroundColor = backgroundColor,
        modifier = modifier
    )
}