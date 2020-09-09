package dev.iotarho.deezerapp.ui.album

import android.util.Log
import androidx.lifecycle.*
import dev.iotarho.deezerapp.api.DeezerRepository
import dev.iotarho.deezerapp.models.AlbumData
import dev.iotarho.deezerapp.models.WrapperResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumViewModel(private val deezerRepository: DeezerRepository) : ViewModel() {

    val loading: LiveData<Boolean>
        get() = mutableLoading
    private val mutableLoading = MutableLiveData<Boolean>()

    val albumsLiveData: LiveData<WrapperResult<AlbumData>>
        get() = albumsData
    private val albumsData = MutableLiveData<WrapperResult<AlbumData>>()


    fun getAllAlbums(artistId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                mutableLoading.postValue(true)
                val results = deezerRepository.getAlbums(artistId)
                albumsData.postValue(results)
                mutableLoading.postValue(false)
            } catch (ex: Exception) {
                mutableLoading.postValue(false)
                Log.e("AlbumViewModel", "Error when fetching the results.")
            }
        }
    }
}