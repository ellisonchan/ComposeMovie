package com.ellison.composemovie.bean

data class Movie (
    var Title: String,
    var Year: String,
    var imdbID: String,
    var Type: String = "",
    var Poster: String
)

val recommendedMovies = listOf(
    Movie(
        "Nomadland",
        "2020",
        "tt9770150",
        "movie",
        "https://img9.doubanio.com/view/photo/l/public/p2632272094.webp"
        // "https://m.media-amazon.com/images/M/MV5BMDRiZWUxNmItNDU5Yy00ODNmLTk0M2ItZjQzZTA5OTJkZjkyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_SX300.jpg"
    ),
    Movie(
        "Soul",
        "2020",
        "tt2948372",
        "movie",
        "https://img2.doubanio.com/view/photo/l/public/p2623266612.webp"
        // "https://m.media-amazon.com/images/M/MV5BZGE1MDg5M2MtNTkyZS00MTY5LTg1YzUtZTlhZmM1Y2EwNmFmXkEyXkFqcGdeQXVyNjA3OTI0MDc@._V1_SX300.jpg"
    ),Movie(
        "Spider-Man: Into the Spider-Verse",
        "2018",
        "tt4633694",
        "movie",
        "https://m.media-amazon.com/images/M/MV5BMjMwNDkxMTgzOF5BMl5BanBnXkFtZTgwNTkwNTQ3NjM@._V1_SX300.jpg"
    )
)

val testMovies = listOf(
    Movie(
        "Bruce Lee: A Warrior's Journey",
        "2000",
        "tt0297814",
        "movie",
        "https://m.media-amazon.com/images/M/MV5BMTI4MDYzMTAxNF5BMl5BanBnXkFtZTcwMTE4MjAyMQ@@._V1_SX300.jpg"
    ),Movie(
        "Dragon: The Bruce Lee Story",
        "1993",
        "tt0106770",
        "movie",
        "https://m.media-amazon.com/images/M/MV5BMjA1MTQxNzAtM2MyOC00NDBhLWFlNmEtOWZlM2E5MjNlODU2XkEyXkFqcGdeQXVyNDAxNjkxNjQ@._V1_SX300.jpg"
    ),

    Movie(
        "I Am Bruce Lee",
        "2012",
        "tt1954299",
        "movie",
        "https://m.media-amazon.com/images/M/MV5BMTcyNTgzMzUwNl5BMl5BanBnXkFtZTcwMDU1MTkyNw@@._V1_SX300.jpg"
    )
)

val testMovies2 = listOf(
    Movie(
        "Harry Potter and the Deathly Hallows: Part 2",
        "2011",
        "tt1201607",
        "movie",
        "https://m.media-amazon.com/images/M/MV5BMGVmMWNiMDktYjQ0Mi00MWIxLTk0N2UtN2ZlYTdkN2IzNDNlXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_SX300.jpg"
    ),Movie(
        "Harry Potter and the Sorcerer's Stone",
        "2001",
        "tt0241527",
        "movie",
        "https://m.media-amazon.com/images/M/MV5BNjQ3NWNlNmQtMTE5ZS00MDdmLTlkZjUtZTBlM2UxMGFiMTU3XkEyXkFqcGdeQXVyNjUwNzk3NDc@._V1_SX300.jpg"
    ),

    Movie(
        "Harry Potter and the Chamber of Secrets",
        "2002",
        "tt0295297",
        "movie",
        "https://m.media-amazon.com/images/M/MV5BMTcxODgwMDkxNV5BMl5BanBnXkFtZTYwMDk2MDg3._V1_SX300.jpg"
    )
)