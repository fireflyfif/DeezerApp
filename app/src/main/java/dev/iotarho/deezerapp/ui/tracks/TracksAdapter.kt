package dev.iotarho.deezerapp.ui.tracks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.TrackData

class TracksAdapter(
    private val tracks: List<TrackData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_track, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentTrack = this.tracks[position]
        if (holder is TrackViewHolder) {
            var showVolume = false
            tracks.forEach { track ->
                if (track.diskNumber > 1) {
                    showVolume = true
                }
            }
            holder.render(currentTrack, showVolume)
        }
    }

    override fun getItemCount(): Int = tracks.size

}
