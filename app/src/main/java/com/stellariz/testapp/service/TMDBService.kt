package com.stellariz.testapp.service

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object TMDBService {
    private const val TMDB_URL = "https://api.themoviedb.org/3/"

    val tmdbService :TMDBRetrofitService = Retrofit.Builder()
        .baseUrl(TMDB_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build().create(TMDBRetrofitService::class.java)
}