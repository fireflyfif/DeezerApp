package dev.iotarho.deezerapp.ui.tracks

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.iotarho.deezerapp.R
import kotlinx.android.synthetic.main.activity_album_tracks.*

class AlbumTracksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_tracks)

        val albumId = intent.getStringExtra(ARG_ALBUM_ID) ?: return
        val artistName = intent.getStringExtra(ARG_ARTIST_NAME) ?: return
        val albumName = intent.getStringExtra(ARG_ALBUM_NAME) ?: return
        val albumUrl = intent.getStringExtra(ARG_ALBUM_IMAGE) ?: return

        val albumTracksFragment =
            AlbumTracksFragment.newInstance(albumId, artistName, albumName, albumUrl)
        supportFragmentManager.beginTransaction()
            .add(albumsHostFragment.id, albumTracksFragment, "AlbumTracksFragment")
            .commit()
    }

    companion object {

        private const val ARG_ALBUM_ID = "album_id"
        private const val ARG_ARTIST_NAME = "artist_name"
        private const val ARG_ALBUM_NAME = "album_name"
        private const val ARG_ALBUM_IMAGE = "album_image"

        fun newInstance(
            context: Context,
            albumId: String,
            artistName: String,
            albumName: String,
            albumUrl: String
        ): Intent {
            val intent = Intent(context, AlbumTracksActivity::class.java)
            val args = Bundle()
            args.putString(ARG_ALBUM_ID, albumId)
            args.putString(ARG_ARTIST_NAME, artistName)
            args.putString(ARG_ALBUM_NAME, albumName)
            args.putString(ARG_ALBUM_IMAGE, albumUrl)
            intent.putExtras(args)
            return intent
        }
    }
}