package com.ellison.composemovie.bean

import com.ellison.composemovie.R

data class Cast (
    var Icon: Int,
    var Name: String,
    var movies: List<Movie>
)

val testCast1: Cast = Cast(
        R.drawable.ic_bruce,
        "Bruce Lee",
    testMovies
    )

val testCast2: Cast = Cast(
    R.drawable.ic_daniel,
    "Daniel Jacob",
    testMovies2
)