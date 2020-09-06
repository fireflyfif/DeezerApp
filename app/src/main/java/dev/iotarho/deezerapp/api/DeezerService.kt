package dev.iotarho.deezerapp.api

import dev.iotarho.deezerapp.models.SearchResult
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DeezerService {

    @GET("search/{type}") // search/artist?q=metalica
    suspend fun searchResults(
        @Path("type") type: String,
        @Query("q") queryWord: String
    ): SearchResult

    @GET("artist/{id}")
    suspend fun artists(@Path("id") id: Int): String

}