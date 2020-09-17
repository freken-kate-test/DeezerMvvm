package ru.test.lightdeezer.data.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import ru.test.lightdeezer.data.models.*

interface DeezerApi {

    @GET("user/5/playlists")
    fun getPlaylists(): Observable<Data>

    @GET("https://api.deezer.com/playlist/{playlistId}")
    fun getPlaylistInfo(@Path("playlistId") playlistId: String): Observable<Playlist>

    @GET("https://api.deezer.com/playlist/{playlistId}/tracks")
    fun getTracklist(@Path("playlistId") playlistId: String): Observable<Tracklist>


}