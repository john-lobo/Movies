package com.jlndev.movies.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jlndev.movies.core.data.local.dao.MovieDao
import com.jlndev.movies.core.data.local.entity.MovieEntity


@Database (
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao


}