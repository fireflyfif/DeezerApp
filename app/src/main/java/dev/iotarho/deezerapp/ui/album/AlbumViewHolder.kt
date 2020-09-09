package dev.iotarho.deezerapp.ui.album

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.AlbumData

class AlbumViewHolder(itemView: View, clickHandler: OnAlbumClickListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val clickHandler: OnAlbumClickListener
    private lateinit var album: AlbumData
    private val albumName: TextView = itemView.findViewById(R.id.albumName)
    private val artistNameTextView: TextView = itemView.findViewById(R.id.artistName)
    private val albumImage: ImageView = itemView.findViewById(R.id.albumImage)

    init {
        itemView.setOnClickListener(this)
        this.clickHandler = clickHandler
    }

    fun render(album: AlbumData, artistName: String) {
        this.album = album
        albumName.text = album.title
        artistNameTextView.text = artistName
        Picasso.get()
            .load(album.coverBig)
            .error(R.drawable.ic_launcher_foreground)
            .into(albumImage)
    }

    override fun onClick(v: View?) {
        clickHandler.onAlbumClick(album)
    }
}