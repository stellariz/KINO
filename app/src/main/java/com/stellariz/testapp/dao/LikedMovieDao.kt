package com.stellariz.testapp.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stellariz.testapp.entities.LikedMovie

@Dao
interface LikedMovieDao {
    @Query("SELECT EXISTS(SELECT * FROM LikedMovie WHERE movie_id = :movieId)")
    fun isMovieLiked(movieId : Int) : Boolean
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg movies: LikedMovie)
    @Delete
    fun deleteLikedMovie(vararg movies: LikedMovie)
}