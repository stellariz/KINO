package com.stellariz.testapp.tmdbService

import com.stellariz.testapp.model.MovieFullData
import com.stellariz.testapp.model.PopularMovies
import io.reactivex.Observable
import retrofit2.http.*

interface TMDBRetrofitService {
    @GET("movie/popular")
    fun getPopularMovies(@Header("Authorization") token: String): Observable<PopularMovies>

    @GET("movie/{movieId}")
    fun getMovieById(@Header("Authorization") token: String,
                     @Path("movieId") movieId: Int) : Observable<MovieFullData>

    @GET("search/movie")
    fun getMovieByName(@Header("Authorization") token: String,
                       @Query("query") movieName: String) : Observable<PopularMovies>
}