package ru.test.lightdeezer.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import androidx.room.Room.databaseBuilder
import ru.test.lightdeezer.database.AppDatabase
import ru.test.lightdeezer.ui.playlist.PLaylistsViewModel
import ru.test.lightdeezer.ui.playlist.PlaylistItemClickListener

class CustomFactory(private val activity: AppCompatActivity, onItemClickListener: PlaylistItemClickListener) :
    ViewModelProvider.NewInstanceFactory() {
    private val mOnItemClickListener: PlaylistItemClickListener

    init {
        mOnItemClickListener = onItemClickListener
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PLaylistsViewModel::class.java)) {
            val db = databaseBuilder(activity.applicationContext, AppDatabase::class.java, "playlists").build()
            @Suppress("UNCHECKED_CAST")
            return PLaylistsViewModel(mOnItemClickListener, db.playlistDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}