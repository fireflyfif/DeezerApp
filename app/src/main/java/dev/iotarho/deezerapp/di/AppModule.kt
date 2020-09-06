package dev.iotarho.deezerapp.di

import dev.iotarho.deezerapp.utils.Constants.BASE_URL
import dev.iotarho.deezerapp.ui.ResultsViewModel
import dev.iotarho.deezerapp.api.DeezerRepository
import dev.iotarho.deezerapp.api.DeezerService
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppModules {

    val searchModule = module {
        single { DeezerRepository(get()) }
        viewModel { ResultsViewModel(get()) }
    }

    val retrofitModule = module {    // 1
        single {   // 2
            okHttp()  // 3
        }

        single {
            retrofit(BASE_URL)  // 4
        }

        single {
            get<Retrofit>().create(DeezerService::class.java)   // 5
        }
    }

    private fun okHttp() = OkHttpClient.Builder()
        .build()

    private fun retrofit(baseUrl: String) = Retrofit.Builder()
        .callFactory(OkHttpClient.Builder().build())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())  // 6
        .build()
}