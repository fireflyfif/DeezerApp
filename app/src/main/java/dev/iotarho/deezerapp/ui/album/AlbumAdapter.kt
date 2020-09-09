package dev.iotarho.deezerapp.ui.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.AlbumData

class AlbumAdapter(
    private val values: List<AlbumData>,
    private val artistName: String,
    private val OnAlbumClickListener: OnAlbumClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view, OnAlbumClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = values[position]
        if (holder is AlbumViewHolder) {
            holder.render(result, artistName)
        }
    }

    override fun getItemCount(): Int = values.size

}

interface OnAlbumClickListener {
    fun onAlbumClick(albums: AlbumData)
}