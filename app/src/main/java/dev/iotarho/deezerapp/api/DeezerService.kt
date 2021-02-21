package dev.iotarho.deezerapp.api

import dev.iotarho.deezerapp.models.AlbumData
import dev.iotarho.deezerapp.models.ResultData
import dev.iotarho.deezerapp.models.TrackData
import dev.iotarho.deezerapp.models.WrapperResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DeezerService {

    @GET("search/artist") // search/artist?q=metalica
    suspend fun searchArtists(@Query("q") queryWord: String): WrapperResult<ResultData>

    @GET("artist/{id}/albums") // artist/119/albums
    suspend fun getAlbums(@Path("id") artistId: String): WrapperResult<AlbumData>

    @GET("album/{id}/tracks") // album/14581202/tracks
    suspend fun getAlbumTracks(@Path("id") albumId: String): WrapperResult<TrackData>

}