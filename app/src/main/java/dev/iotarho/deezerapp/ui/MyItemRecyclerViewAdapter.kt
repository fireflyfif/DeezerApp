package dev.iotarho.deezerapp.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.Result


class MyItemRecyclerViewAdapter(
    private val values: List<Result>
) : RecyclerView.Adapter<MyItemRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.artistName.text = item.name
        Picasso.get()
            .load(item.pictureSmall)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.artistImage)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val artistName: TextView = view.findViewById(R.id.artistName)
        val artistImage: ImageView = view.findViewById(R.id.artistImage)
    }
}