package dev.iotarho.deezerapp.ui.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.ResultData


class ArtistsAdapter(
    private val values: List<ResultData>,
    private val onResultClickListener: OnResultClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_artist, parent, false)
        return ResultViewHolder(view, onResultClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val result = values[position]
        if (holder is ResultViewHolder) {
            holder.render(result)
        }
    }

    override fun getItemCount(): Int = values.size

}

interface OnResultClickListener {
    fun onResultClick(result: ResultData)
}