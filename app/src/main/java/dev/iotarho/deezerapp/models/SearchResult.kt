package dev.iotarho.deezerapp.models

import com.google.gson.annotations.SerializedName

data class SearchResult (
    @SerializedName("data") val data : List<Result>,
    @SerializedName("total") val total : Int
)


