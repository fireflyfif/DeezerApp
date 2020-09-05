package dev.iotarho.deezerapp.models

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_small") val picture_small: String,
    @SerializedName("picture_medium") val picture_medium: String,
    @SerializedName("picture_big") val picture_big: String,
    @SerializedName("picture_xl") val picture_xl: String,
    @SerializedName("nb_album") val nb_album: Int,
    @SerializedName("nb_fan") val nb_fan: Int,
    @SerializedName("radio") val radio: Boolean,
    @SerializedName("tracklist") val tracklist: String,
    @SerializedName("type") val type: String
)