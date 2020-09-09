package dev.iotarho.deezerapp.ui.tracks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.iotarho.deezerapp.api.DeezerRepository
import dev.iotarho.deezerapp.models.TrackData
import dev.iotarho.deezerapp.models.WrapperResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TracksViewModel(private val deezerRepository: DeezerRepository) : ViewModel(){

    val loading: LiveData<Boolean>
        get() = mutableLoading
    private val mutableLoading = MutableLiveData<Boolean>()

    val tracksLiveData: LiveData<WrapperResult<TrackData>>
        get() = tracksData
    private val tracksData = MutableLiveData<WrapperResult<TrackData>>()


    fun getAlbumTracks(artistId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mutableLoading.postValue(true)
                val results = deezerRepository.getAlbumTracks(artistId)
                tracksData.postValue(results)
                mutableLoading.postValue(false)
            } catch (ex: Exception) {
                mutableLoading.postValue(false)
                Log.e("TracksViewModel", "Error when fetching the results.")
            }
        }
    }
}