package ru.test.lightdeezer.ui.playlist

import androidx.lifecycle.MutableLiveData
import ru.test.lightdeezer.common.BaseViewModel
import ru.test.lightdeezer.data.models.Playlist

class PlaylistInfoViewModel() : BaseViewModel() {
    private val playlistTitle = MutableLiveData<String>()
    private val playlistCover = MutableLiveData<String>()
    private val playlistCoverMedium = MutableLiveData<String>()
    private val playlistId = MutableLiveData<String>()

    fun bind(playlist: Playlist) {
        playlistTitle.value = playlist.title
        playlistCover.value = playlist.picture_small
        playlistCoverMedium.value = playlist.picture_medium
        playlistId.value = playlist.id.toString()
    }

    fun getPlaylistTitle(): MutableLiveData<String> {
        return playlistTitle
    }

    fun getPlaylistCover(): MutableLiveData<String> {
        return playlistCover
    }

    fun getPlaylistCoverMedium() : MutableLiveData<String>{
        return playlistCoverMedium
    }

    fun getPlaylistId() : MutableLiveData<String>{
        return playlistId
    }
}