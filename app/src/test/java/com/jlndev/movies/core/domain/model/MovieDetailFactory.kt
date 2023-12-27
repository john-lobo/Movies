package com.jlndev.movies.core.domain.model

class MovieDetailFactory {

    fun create(poster: Poster) = when (poster) {
        Poster.Avengers -> {
            MovieDetails(
                id = 1,
                title = "Homem Aranha",
                genres = listOf("Ação", "Aventura", "Aventura", "Aventura", "Aventura", "Aventura"),
                overview = "Depois do nosso herói ter sido desmascarado pelo seu inimigo Mysterio, Peter Parker já não é capaz de separar a sua vida normal do seu estatuto de super-herói, pelo que só lhe resta pedir ajuda ao Mestre das Artes Místicas, o Doutor Estranho, para que apague a sua real identidade da cabeça de todos. No entanto, esse feitiço leva-o a uma realidade que não é a sua e onde terá de enfrentar novos inimigos, ainda mais perigosos, forçando-o a descobrir o que realmente significa ser o Homem-Aranha.",
                backdropPathUrl = "",
                releaseDate = "25/05/2022",
                voteAverage = 1.4
            )
        }

        Poster.JohnWick -> {
            MovieDetails(
                id = 2,
                title = "John Wick",
                genres = listOf("Ação", "Aventura", "Aventura", "Aventura", "Aventura", "Aventura"),
                overview = "Depois do nosso herói ter sido desmascarado pelo seu inimigo Mysterio, Peter Parker já não é capaz de separar a sua vida normal do seu estatuto de super-herói, pelo que só lhe resta pedir ajuda ao Mestre das Artes Místicas, o Doutor Estranho, para que apague a sua real identidade da cabeça de todos. No entanto, esse feitiço leva-o a uma realidade que não é a sua e onde terá de enfrentar novos inimigos, ainda mais perigosos, forçando-o a descobrir o que realmente significa ser o Homem-Aranha.",
                backdropPathUrl = "",
                releaseDate = "25/05/2022",
                voteAverage = 1.4
            )
        }
    }

    sealed class Poster {
        object Avengers : Poster()
        object JohnWick : Poster()
    }
}