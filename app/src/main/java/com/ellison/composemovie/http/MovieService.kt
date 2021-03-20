package com.ellison.composemovie.http

import android.content.Context
import com.ellison.composemovie.bean.*
import com.ellison.composemovie.constant.Constants
import com.ellison.composemovie.util.Utils
import kotlin.jvm.Volatile
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class MovieService private constructor(context: Context) {
    val movieInterface: MovieRequestService
    private val context: Context

    suspend fun searchMoviesByCoroutines(keyWorld: String): MovieResponse<List<Movie>> {
        Utils.logDebug(Utils.TAG_NETWORK, "searchMoviesByCoroutines:$keyWorld")
        return movieInterface?.requestSearchByCoroutines(keyWorld, Constants.OMDB_API_KEY)
    }

    suspend fun getMovieByCoroutines(movieID: String): MoviePro {
        Utils.logDebug(Utils.TAG_NETWORK, "getMovieByCoroutines:$movieID")
        return movieInterface?.requestDetailByCoroutines(movieID, Constants.OMDB_API_KEY)
    }

    companion object {
        @Volatile
        private var sInstance: MovieService? = null

        fun getInstance(context: Context): MovieService? {
            if (sInstance == null) {
                synchronized(MovieService::class.java) {
                    if (sInstance == null) {
                        sInstance = MovieService(context)
                    }
                }
            }
            return sInstance
        }
    }

    init {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retrofit = Retrofit.Builder()
            .baseUrl(Constants.OMDB_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieInterface = retrofit.create(MovieRequestService::class.java)
        this.context = context
    }
}