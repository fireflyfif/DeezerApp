package dev.iotarho.deezerapp.models

import com.google.gson.annotations.SerializedName

data class Result(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_xl") val pictureExtraBig: String,
    @SerializedName("nb_album") val nbAlbum: Int,
    @SerializedName("nb_fan") val nbFan: Int,
    @SerializedName("radio") val radio: Boolean,
    @SerializedName("tracklist") val trackList: String,
    @SerializedName("type") val type: String
)