package com.example.musicapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.musicapp.model.Favourites

@Database(entities = [Favourites::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getFavs(): FavDao

    companion object {
        private const val DB_NAME = "musicApp.db"
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java, DB_NAME
                )
                    .build()
            }
            return instance
        }
    }

}