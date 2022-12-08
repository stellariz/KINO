package com.stellariz.testapp.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LikedMovie(
    @PrimaryKey @ColumnInfo(name = "movie_id") val movieId: Int,
)
