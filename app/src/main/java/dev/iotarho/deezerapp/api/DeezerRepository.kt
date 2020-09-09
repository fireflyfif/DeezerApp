package dev.iotarho.deezerapp.api

import dev.iotarho.deezerapp.models.AlbumData
import dev.iotarho.deezerapp.models.ResultData
import dev.iotarho.deezerapp.models.TrackData
import dev.iotarho.deezerapp.models.WrapperResult

class DeezerRepository (private val deezerService: DeezerService) {

    suspend fun getArtists(query: String): WrapperResult<ResultData> {
        return deezerService.searchArtists(query)
    }

    suspend fun getAlbums(artistId: String): WrapperResult<AlbumData> {
        return deezerService.getAlbums(artistId)
    }

    suspend fun getAlbumTracks(albumId: String): WrapperResult<TrackData> {
        return deezerService.getAlbumTracks(albumId)
    }
}