package ru.test.lightdeezer.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.test.lightdeezer.data.models.Playlist

@Database(entities = arrayOf(Playlist::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
}