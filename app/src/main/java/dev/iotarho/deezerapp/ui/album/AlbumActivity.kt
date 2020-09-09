package dev.iotarho.deezerapp.ui.album

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.iotarho.deezerapp.R
import kotlinx.android.synthetic.main.activity_album.*

class AlbumActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album)

        val artistName = intent.getStringExtra(ARG_ARTIST_NAME) ?: return
        val artistId = intent.getStringExtra(ARG_ARTIST_ID) ?: return

        val albumFragment = AlbumsFragment.newInstance(artistId, artistName)
        supportFragmentManager.beginTransaction()
            .add(albumHostFragment.id, albumFragment, "AlbumsFragment")
            .commit()
    }

    companion object {

        private const val ARG_ARTIST_ID = "artist_id"
        private const val ARG_ARTIST_NAME = "artist_name"

        fun newInstance(context: Context, artistId: String, artistName: String): Intent {
            val intent = Intent(context, AlbumActivity::class.java)
            val args = Bundle()
            args.putString(ARG_ARTIST_ID, artistId)
            args.putString(ARG_ARTIST_NAME, artistName)
            intent.putExtras(args)
            return intent
        }
    }
}