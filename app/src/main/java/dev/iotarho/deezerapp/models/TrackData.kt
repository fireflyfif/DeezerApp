package dev.iotarho.deezerapp.models

import com.google.gson.annotations.SerializedName

data class TrackData(

    @SerializedName("id") val id: Int,
    @SerializedName("readable") val readable: Boolean,
    @SerializedName("title") val title: String,
    @SerializedName("title_short") val titleShort: String,
    @SerializedName("title_version") val titleVersion: String,
    @SerializedName("isrc") val isrc: String,
    @SerializedName("link") val link: String,
    @SerializedName("duration") val duration: Int,
    @SerializedName("track_position") val trackPosition: Int,
    @SerializedName("disk_number") val diskNumber: Int,
    @SerializedName("rank") val rank: Int,
    @SerializedName("explicit_lyrics") val explicitLyrics: Boolean,
    @SerializedName("explicit_content_lyrics") val explicitContentLyrics: Int,
    @SerializedName("explicit_content_cover") val explicitContentCover: Int,
    @SerializedName("preview") val preview: String,
    @SerializedName("artist") val artist: ArtistData,
    @SerializedName("type") val type: String
)