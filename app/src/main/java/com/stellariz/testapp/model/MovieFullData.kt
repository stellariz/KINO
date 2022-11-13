package com.stellariz.testapp.model

data class MovieFullData(
    val original_title: String,
    val release_date: String,
    val poster_path: String,
    val overview: String,
    val tagline: String,
    val runtime: Int,
    val genres: List<Genre>,
)
