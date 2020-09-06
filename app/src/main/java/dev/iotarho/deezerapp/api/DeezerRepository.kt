package dev.iotarho.deezerapp.api

import dev.iotarho.deezerapp.models.SearchResult

class DeezerRepository (private val deezerService: DeezerService) {

    suspend fun getResults(type: String, query: String): SearchResult {
        return deezerService.searchResults(type, query)
    }
}