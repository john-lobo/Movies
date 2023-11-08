package com.jlndev.movies.ui.views.movie_search_screen.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jlndev.movies.ui.theme.white

@Composable
fun SearchComponent(
    modifier: Modifier = Modifier,
    query: String,
    onSearch: (String) -> Unit,
    onQueryChangeEvent: (String) -> Unit
) {
    OutlinedTextField(
        value = query,
        onValueChange = {
            onQueryChangeEvent(it)
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onSearch(query)
                }
            ) {
                Icon(imageVector = Icons.Outlined.Search , contentDescription = null)
            }
        },
        placeholder = {
            Text(text = "Pesquisa de Filmes")
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            capitalization = KeyboardCapitalization.Sentences,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearch(query)
            },
        ),
        colors = TextFieldDefaults.textFieldColors(
            textColor = white,
            cursorColor = white,
            placeholderColor = white,
            trailingIconColor = white,
            focusedIndicatorColor = white,
            unfocusedIndicatorColor = white
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 60.dp),
    )
}

@Preview
@Composable
fun SearchComponentPreview() {
    SearchComponent(
        query = "",
        onSearch = {},
        onQueryChangeEvent = {}
    )
}