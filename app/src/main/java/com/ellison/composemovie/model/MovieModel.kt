package com.ellison.composemovie.model

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.ellison.composemovie.R
import com.ellison.composemovie.bean.*
import com.ellison.composemovie.http.MovieService
import com.ellison.composemovie.util.Utils

class MovieModel(application: Application) : AndroidViewModel(application) {
    private val movieService = MovieService.getInstance(application.applicationContext)

    private var movieLiveData = MutableLiveData<List<Movie>>()
    private var movieProLiveData = MutableLiveData<MoviePro>()

    val movies: LiveData<List<Movie>> = movieLiveData
    val moviePro: LiveData<MoviePro> = movieProLiveData

    suspend fun searchMoviesComposeCoroutines(keyWorld: String) {
        Utils.logDebug(Utils.TAG_SEARCH, "MovieModel searchMoviesComposeCoroutines with keyWord:$keyWorld")
        if (!Utils.ensureNetworkAvailable(getApplication())) return

        val gotMovies = movieService?.searchMoviesByCoroutines(keyWorld)?.Search
        Utils.logDebug(
            Utils.TAG_SEARCH,
            "MovieModel searchMoviesCoroutines gotMovies:$gotMovies"
        )

        if (gotMovies != null)
            movieLiveData.value = gotMovies
        else
            Toast.makeText(getApplication(), R.string.search_none, Toast.LENGTH_SHORT)
                .show()
    }

    suspend fun getMovieComposeCoroutines(id: String) {
        Utils.logDebug(Utils.TAG_SEARCH, "MovieModel getMovieComposeCoroutines with id:$id")
        if (!Utils.ensureNetworkAvailable(getApplication())) return

        val gotMovie = movieService?.getMovieByCoroutines(id)
        Utils.logDebug(Utils.TAG_SEARCH, "MovieModel getMovieComposeCoroutines movie:$gotMovie")

        if (gotMovie != null)
            movieProLiveData.value = gotMovie
        else
            Toast.makeText(getApplication(), R.string.search_none, Toast.LENGTH_SHORT)
                .show()
    }
}