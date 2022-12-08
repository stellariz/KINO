package com.stellariz.testapp.databases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stellariz.testapp.dao.LikedMovieDao
import com.stellariz.testapp.entities.LikedMovie

@Database(entities = [LikedMovie::class], version = 1)
abstract class LikedMovieDatabase : RoomDatabase() {
    abstract fun likedMovieDao() : LikedMovieDao
}