package dev.iotarho.deezerapp.ui.album

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.AlbumData
import dev.iotarho.deezerapp.models.WrapperResult
import dev.iotarho.deezerapp.ui.tracks.AlbumTracksActivity
import org.koin.android.viewmodel.ext.android.viewModel


class AlbumsFragment : Fragment(), OnAlbumClickListener {

    private var artistId: String? = null
    private var artistName: String? = null
    private lateinit var albumsList: RecyclerView
    private val albumsViewModel: AlbumViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            artistId = it.getString(ARG_ARTIST_ID)
            artistName = it.getString(ARG_ARTIST_NAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_albums, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarAlbum)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        albumsList = view.findViewById(R.id.albumsList)

        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)?.title = "Albums"

        albumsViewModel.loading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        albumsViewModel.albumsLiveData.observe(viewLifecycleOwner) {
            setupAlbums(it)
        }

        artistId?.let { albumsViewModel.getAllAlbums(it) }

        return view
    }

    private fun setupAlbums(wrapperData: WrapperResult<AlbumData>) {
        // Set the adapter
        with(albumsList) {
            adapter = AlbumAdapter(wrapperData.data, artistName!!, this@AlbumsFragment)
        }
    }

    companion object {
        private const val ARG_ARTIST_ID = "artist_id"
        private const val ARG_ARTIST_NAME = "artist_name"

        fun newInstance(artistId: String, artistName: String) =
            AlbumsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ARTIST_ID, artistId)
                    putString(ARG_ARTIST_NAME, artistName)
                }
            }
    }

    override fun onAlbumClick(albums: AlbumData) {
        startActivity(
            AlbumTracksActivity.newInstance(
                requireContext(),
                albums.id.toString(),
                artistName!!,
                albums.title,
                albums.coverBig
            )
        )
    }
}