package ru.test.lightdeezer.ui.playlist.detail

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.test.lightdeezer.R
import ru.test.lightdeezer.common.BaseViewModel
import ru.test.lightdeezer.data.models.Playlist
import ru.test.lightdeezer.data.models.Track
import ru.test.lightdeezer.data.network.DeezerApi
import javax.inject.Inject

class DetailActivityViewModel(playlistId: String) : BaseViewModel() {
    @Inject
    lateinit var deezerApi: DeezerApi
    var detailViewModel: DetailViewModel = DetailViewModel()
    val tracklistAdapter = TracklistAdapter()
    private var id = playlistId
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        loadPlaylistInfo()
        loadTracklist()
    }

    private fun loadTracklist() {
        subscription = deezerApi.getTracklist(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onRetrieveTracklistStart() }
//            .doOnTerminate { onRetrieveTracklistFinish() }
            .subscribe(
                { result -> onRetrieveTracklistSuccess(result.data) },
                { onRetrieveTracklistError() }
            )
    }

    private fun onRetrieveTracklistError() {
        errorMessage.value = R.string.playlist_error
    }

    private fun onRetrieveTracklistSuccess(result: List<Track>) {
        tracklistAdapter.updateTracklist(result)
    }

    private fun loadPlaylistInfo() {
        subscription = deezerApi.getPlaylistInfo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onRetrieveTracklistInfoStart() }
//            .doOnTerminate { onRetrieveTracklistInfoFinish() }
            .subscribe(
                { result -> onRetrievePlaylistSuccess(result)},
                { onRetrieveTracklistError() }
            )
    }

    private fun onRetrievePlaylistSuccess(result: Playlist) {
     //todo not implemented
    }
}