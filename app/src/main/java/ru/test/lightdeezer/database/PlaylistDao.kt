package ru.test.lightdeezer.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.test.lightdeezer.data.models.Playlist

@Dao
interface PlaylistDao {
    @get:Query("SELECT * FROM playlist")
    val all: List<Playlist>

    @Insert
    fun insertAll(vararg playlists: Playlist)
}