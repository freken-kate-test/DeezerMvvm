package ru.test.lightdeezer.ui.playlist.detail

import androidx.lifecycle.MutableLiveData
import ru.test.lightdeezer.common.BaseViewModel

class DetailViewModel() : BaseViewModel() {
    private val playlistCoverMedium = MutableLiveData<String>()

    fun bind(playlistCover: String) {
        playlistCoverMedium.value = playlistCover
    }

    fun getPlaylistCoverMedium(): MutableLiveData<String> {
        return playlistCoverMedium
    }

}