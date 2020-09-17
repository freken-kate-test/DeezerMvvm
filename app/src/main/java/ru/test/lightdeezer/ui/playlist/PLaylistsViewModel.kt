package ru.test.lightdeezer.ui.playlist

import android.view.View
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.test.lightdeezer.R
import ru.test.lightdeezer.common.BaseViewModel
import ru.test.lightdeezer.data.models.Playlist
import ru.test.lightdeezer.data.network.DeezerApi
import ru.test.lightdeezer.database.PlaylistDao
import javax.inject.Inject

class PLaylistsViewModel(val playlistClickListener: PlaylistItemClickListener, private val playlistDao: PlaylistDao) : BaseViewModel() {
    @Inject
    lateinit var deezerApi: DeezerApi

    private lateinit var subscription: Disposable

    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage:MutableLiveData<Int> = MutableLiveData()
    val playlistAdapter = PlaylistAdapter(playlistClickListener)

    init {
        loadPlaylists()
    }

    private fun loadPlaylists() {
        subscription =
            Observable.fromCallable { playlistDao.all }
                .concatMap {
                        dbPlaylist ->
                    if(dbPlaylist.isEmpty())
                        deezerApi.getPlaylists().concatMap {
                                apiPlaylist -> playlistDao.insertAll(*apiPlaylist.data.toTypedArray())
                            Observable.just(apiPlaylist.data)
                        }
                    else
                        Observable.just(dbPlaylist)
                }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result -> onRetrievePlaylistListSuccess(result) },
                { onRetrievePlaylistListError() }
            )
    }

    private fun onRetrievePostListStart() {
        loadingVisibility.value = View.VISIBLE
    }

    private fun onRetrievePostListFinish() {
        loadingVisibility.value = View.GONE
    }

    private fun onRetrievePlaylistListSuccess(playlist: List<Playlist>) {
        playlistAdapter.updatePlaylist(playlist)
    }

    private fun onRetrievePlaylistListError() {
        errorMessage.value = R.string.playlist_error
    }

    fun onItemClickListener(): PlaylistItemClickListener{
        return playlistClickListener
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}