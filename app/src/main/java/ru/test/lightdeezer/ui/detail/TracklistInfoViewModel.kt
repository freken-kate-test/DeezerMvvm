package ru.test.lightdeezer.ui.playlist.detail

import android.text.format.DateUtils
import androidx.lifecycle.MutableLiveData
import ru.test.lightdeezer.common.BaseViewModel
import ru.test.lightdeezer.data.models.Track
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

class TracklistInfoViewModel : BaseViewModel() {
    private val trackTitle = MutableLiveData<String>()
    private val trackDuration = MutableLiveData<String>()

    fun bind(track: Track){
        trackTitle.value = track.title
        trackDuration.value = getTrackDurationFormatted(track.duration)
    }

    fun getTrackTitle(): MutableLiveData<String>{
        return trackTitle
    }

    fun getTrackDuration():MutableLiveData<String>{
        return trackDuration
    }

    fun getTrackDurationFormatted(stringTrackDuration : String) : String? {
        val trackDurationInSeconds = stringTrackDuration.toLong()
        return DateUtils.formatElapsedTime(trackDurationInSeconds)
    }

}