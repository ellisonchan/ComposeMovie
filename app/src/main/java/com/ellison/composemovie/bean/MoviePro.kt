package com.ellison.composemovie.bean

data class MoviePro (
    var Title: String,
    var Year: String = "",
    var Rated: String = "",
    var Released: String = "",
    var Runtime: String = "",
    var Genre: String = "",
    var Director: String = "",
    var Writer: String = "",
    var Actors: String = "",
    var Plot: String = "",
    var Language: String = "",
    var Country: String = "",
    var Poster: String = "",
    var Metascore: String = "",
    var imdbRating: String = "",
    var imdbVotes: String = "",
    var imdbID: String = "",
    var Type: String = "",
    var DVD: String = "",
    var BoxOffice: String = "",
    var Production: String = "",
    var Website: String = "",
    var Response: String = ""
) {
   override fun toString(): String {
       return "MovieProResponse{" +
               "Title='" + Title + '\'' +
               ", Year='" + Year + '\'' +
               ", Rated='" + Rated + '\'' +
               ", Released='" + Released + '\'' +
               ", Runtime='" + Runtime + '\'' +
               ", Genre='" + Genre + '\'' +
               ", Director='" + Director + '\'' +
               ", Writer='" + Writer + '\'' +
               ", Actors='" + Actors + '\'' +
               ", Plot='" + Plot + '\'' +
               ", Language='" + Language + '\'' +
               ", Country='" + Country + '\'' +
               ", Poster='" + Poster + '\'' +
               ", Metascore='" + Metascore + '\'' +
               ", imdbRating='" + imdbRating + '\'' +
               ", imdbVotes='" + imdbVotes + '\'' +
               ", imdbID='" + imdbID + '\'' +
               ", Type='" + Type + '\'' +
               ", DVD='" + DVD + '\'' +
               ", BoxOffice='" + BoxOffice + '\'' +
               ", Production='" + Production + '\'' +
               ", Website='" + Website + '\'' +
               ", Response='" + Response + '\'' +
               '}'
   }
}

val testMoviePro =
    MoviePro(
        "Harry Potter Part 2",
        "2011",
        "PG-13",
        "15 Jul 2011",
        "130 min",
        "Adventure, Drama, Fantasy, Mystery",
        "David Yates",
        "Steve Kloves (screenplay), J.K. Rowling (novel)",
        "Ralph Fiennes, Michael Gambon, Alan Rickman, Daniel Radcliffe",
        "Harry, Ron, and Hermione search for Voldemort's remaining Horcruxes in their effort to destroy the Dark Lord as the final battle rages on at Hogwarts.",
        "English",
        "UK",
        // "Nominated for 3 Oscars. Another 46 wins & 91 nominations.",
        "https://m.media-amazon.com/images/M/MV5BMGVmMWNiMDktYjQ0Mi00MWIxLTk0N2UtN2ZlYTdkN2IzNDNlXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_SX300.jpg",
        "85",
        "8.1",
        "766,594",
        "tt1201607",
        "movie",
        "24 Jul 2014",
        "$381,409,310",
        "Heyday Films, Moving Picture Company, Warner Bros.",
        "N/A",
    "true"
    )

val favouriteMovies = listOf(
    MoviePro(
        "Harry Potter Part 2",
        "2011",
        "PG-13",
        "15 Jul 2011",
        "130 min",
        "Adventure, Drama, Fantasy, Mystery",
        "David Yates",
        "Steve Kloves (screenplay), J.K. Rowling (novel)",
        "Ralph Fiennes, Michael Gambon, Alan Rickman, Daniel Radcliffe",
        "Harry, Ron, and Hermione search for Voldemort's remaining Horcruxes in their effort to destroy the Dark Lord as the final battle rages on at Hogwarts.",
        "English",
        "UK",
        // "Nominated for 3 Oscars. Another 46 wins & 91 nominations.",
        "https://m.media-amazon.com/images/M/MV5BMGVmMWNiMDktYjQ0Mi00MWIxLTk0N2UtN2ZlYTdkN2IzNDNlXkEyXkFqcGdeQXVyODE5NzE3OTE@._V1_SX300.jpg",
        "85",
        "8.1",
        "766,594",
        "tt1201607",
        "movie",
        "24 Jul 2014",
        "$381,409,310",
        "Heyday Films, Moving Picture Company, Warner Bros.",
        "N/A",
        "true"
    ),
    MoviePro(
        "Spider-Man: Into the Spider-Verse",
        "2018",
        imdbID = "tt4633694",
        Type = "movie",
        Poster = "https://m.media-amazon.com/images/M/MV5BMjMwNDkxMTgzOF5BMl5BanBnXkFtZTgwNTkwNTQ3NjM@._V1_SX300.jpg"
    ),
    MoviePro(
        "Soul",
        "2020",
        imdbID = "tt2948372",
        Type = "movie",
        Poster = "https://img2.doubanio.com/view/photo/l/public/p2623266612.webp"
    )
)