package dev.iotarho.deezerapp.ui.artist

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.ResultData
import dev.iotarho.deezerapp.ui.artist.OnResultClickListener

class ResultViewHolder(itemView: View, clickHandler: OnResultClickListener) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {

    private val clickHandler: OnResultClickListener
    private lateinit var result: ResultData
    private val artistName: TextView = itemView.findViewById(R.id.artistName)
    private val artistImage: ImageView = itemView.findViewById(R.id.artistImage)

    init {
        itemView.setOnClickListener(this)
        this.clickHandler = clickHandler
    }

    fun render(result: ResultData) {
        this.result = result
        artistName.text = result.name
        Picasso.get()
            .load(result.pictureSmall)
            .error(R.drawable.ic_launcher_foreground)
            .into(artistImage)
    }

    override fun onClick(v: View) {
        clickHandler.onResultClick(result)
    }
}