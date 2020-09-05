package dev.iotarho.deezerapp

import dev.iotarho.deezerapp.models.SearchResult
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

class DeezerService {

    object DeezerApi {
        private const val BASE_URL = "https://api.deezer.com/"
        private val httpClient = OkHttpClient.Builder()
        private val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        private val retrofit = builder.client(httpClient.build()).build()

        val deezerService = retrofit.create(RestAPI::class.java)
    }

    interface RestAPI {

        @GET("search/{type}") // search/artist?q=metalica
        suspend fun searchResults(@Path("type") type: String,
                                  @Query("q") queryWord: String): SearchResult

        @GET("artist/{id}")
        suspend fun artists(@Path("id") id: Int): String
    }
}