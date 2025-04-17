package com.kurly.kurlyassignment.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kurly.kurlyassignment.data.local.dao.FavoriteDao
import com.kurly.kurlyassignment.domain.model.Favorite

@Database(entities = [Favorite::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
}
