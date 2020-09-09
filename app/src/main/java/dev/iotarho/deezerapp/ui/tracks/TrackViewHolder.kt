package dev.iotarho.deezerapp.ui.tracks

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.TrackData

class TrackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val trackName: TextView = itemView.findViewById(R.id.trackName)
    private val trackPosition: TextView = itemView.findViewById(R.id.trackPosition)
    private val artistName: TextView = itemView.findViewById(R.id.artistName)

    private val trackVolume: TextView = itemView.findViewById(R.id.trackVolume)
    private val divider: View = itemView.findViewById(R.id.divider)

    fun render(track: TrackData, showVolume: Boolean) {

        if (showVolume && track.trackPosition == 1) {
            trackVolume.text = itemView.context.getString(R.string.track_volume, track.diskNumber)
            trackVolume.visibility = View.VISIBLE
            divider.visibility = View.VISIBLE
        } else {
            trackVolume.visibility = View.GONE
            divider.visibility = View.GONE
        }

        trackName.text = track.title
        trackPosition.text = track.trackPosition.toString()
        artistName.text = track.artist.name
    }
}