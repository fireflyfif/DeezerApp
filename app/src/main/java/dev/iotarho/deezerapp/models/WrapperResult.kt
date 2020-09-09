package dev.iotarho.deezerapp.models

import com.google.gson.annotations.SerializedName

data class WrapperResult<out T>(
    @SerializedName("data") val data: List<T>,
    @SerializedName("total") val total: Int,
    @SerializedName("next") val next : String
)


