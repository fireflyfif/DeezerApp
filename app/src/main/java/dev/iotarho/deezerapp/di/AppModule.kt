package dev.iotarho.deezerapp.di

import dev.iotarho.deezerapp.utils.Constants.BASE_URL
import dev.iotarho.deezerapp.ui.artist.ResultsViewModel
import dev.iotarho.deezerapp.api.DeezerRepository
import dev.iotarho.deezerapp.api.DeezerService
import dev.iotarho.deezerapp.ui.album.AlbumViewModel
import dev.iotarho.deezerapp.ui.tracks.TracksViewModel
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModules {

    val searchModule = module {
        single { DeezerRepository(get()) }
        viewModel { ResultsViewModel(get()) }
        viewModel { AlbumViewModel(get()) }
        viewModel { TracksViewModel(get()) }
    }

    val retrofitModule = module {
        single {
            okHttp()
        }

        single {
            retrofit(BASE_URL)
        }

        single {
            get<Retrofit>().create(DeezerService::class.java)
        }
    }

    private fun okHttp() = OkHttpClient.Builder()
        .build()

    private fun retrofit(baseUrl: String) = Retrofit.Builder()
        .callFactory(OkHttpClient.Builder().build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}