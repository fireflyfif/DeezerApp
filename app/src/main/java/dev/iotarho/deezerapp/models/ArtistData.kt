package dev.iotarho.deezerapp.models

import com.google.gson.annotations.SerializedName

class ArtistData(

    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("link") val link: String,
    @SerializedName("picture") val picture: String,
    @SerializedName("picture_small") val pictureSmall: String,
    @SerializedName("picture_medium") val pictureMedium: String,
    @SerializedName("picture_big") val pictureBig: String,
    @SerializedName("picture_xl") val pictureBigger: String,
    @SerializedName("tracklist") val trackList: String,
    @SerializedName("type") val type: String

//    @SerializedName("id") val id : Int,
//@SerializedName("name") val name : String,
//@SerializedName("tracklist") val tracklist : String,
//@SerializedName("type") val type : String
)
