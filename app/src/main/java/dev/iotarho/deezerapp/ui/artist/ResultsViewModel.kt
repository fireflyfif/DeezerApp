package dev.iotarho.deezerapp.ui.artist

import android.util.Log
import androidx.lifecycle.*
import dev.iotarho.deezerapp.api.DeezerRepository
import dev.iotarho.deezerapp.models.ResultData
import dev.iotarho.deezerapp.models.WrapperResult
import java.util.*

class ResultsViewModel(private val deezerRepository: DeezerRepository) : ViewModel() {

    val query: LiveData<String>
        get() = queryMutableData
    private val queryMutableData = MutableLiveData<String>()

    val loading: LiveData<Boolean>
        get() = mutableLoading
    private val mutableLoading = MutableLiveData<Boolean>()

    // Under the hood, switchMap uses the MediatorLiveData that removes the initial source
    // whenever the new source is added
    val artistResults: LiveData<WrapperResult<ResultData>> = queryMutableData.switchMap { query ->
        if (query.isNullOrBlank()) {
            // TODO: add an empty screen
            Log.d("ResultsViewModel", "query is null")
        }
        liveData(viewModelScope.coroutineContext) {
            mutableLoading.value = true
            try {
                emit(deezerRepository.getArtists(query))
                mutableLoading.value = false
            } catch (ex: Exception) {
                Log.e("ResultsViewModel", "results is null")
                mutableLoading.value = false
            }
        }
    }


    fun setQuery(originalInput: String) {
        val input = originalInput.toLowerCase(Locale.getDefault()).trim()
        if (input == queryMutableData.value) {
            return
        }
        Log.d("ResultsViewModel", "originalInput is : $originalInput")
        queryMutableData.value = input
    }
}