package dev.iotarho.deezerapp.ui.tracks

import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.TrackData
import dev.iotarho.deezerapp.models.WrapperResult
import kotlinx.android.synthetic.main.fragment_album_tracks.*
import org.koin.android.viewmodel.ext.android.viewModel

class AlbumTracksFragment : Fragment() {

    private var albumId: String? = null
    private var artistName: String? = null
    private var albumName: String? = null
    private var albumUrl: String? = null

    private val tracksViewModel: TracksViewModel by viewModel()
    private lateinit var tracksList: RecyclerView
    private lateinit var albumImage: ImageView
    private lateinit var collapsingLayout: CollapsingToolbarLayout
    private lateinit var artistNameTextView: TextView
    private lateinit var albumNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            albumId = it.getString(ARG_ALBUM_ID)
            artistName = it.getString(ARG_ARTIST_NAME)
            albumName = it.getString(ARG_ALBUM_NAME)
            albumUrl = it.getString(ARG_ALBUM_IMAGE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_album_tracks, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbarTracks)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)
        artistNameTextView = view.findViewById(R.id.artistName)
        albumNameTextView = view.findViewById(R.id.albumName)
        tracksList = view.findViewById(R.id.trackList)
        albumImage = view.findViewById(R.id.albumImage)
        collapsingLayout= view.findViewById(R.id.collapsingToolbar)

        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tracksViewModel.loading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        tracksViewModel.tracksLiveData.observe(viewLifecycleOwner) {
            setupUi(it)
        }

        albumId?.let { tracksViewModel.getAlbumTracks(it) }

        return view
    }

    private fun setupUi(wrapperData: WrapperResult<TrackData>) {
        with(tracksList) {
            adapter = TracksAdapter(wrapperData.data)
        }
        Picasso.get()
            .load(albumUrl)
            .into(albumImage)

        albumName?.let { setCollapsingToolbar(it) }
        artistNameTextView.text = artistName
        albumNameTextView.text = albumName

        setBackground(albumImage)
    }

    private fun setBackground(albumImage: ImageView) {

        // Get the image as a bitmap
        val bitmap = (albumImage.drawable as BitmapDrawable).bitmap
        // Get a color from the bitmap by using the Palette library
        val palette = Palette.from(bitmap).generate()
        val generatedLightColor = palette.getMutedColor(lightMutedColor.toInt())

        val gradientDrawable = GradientDrawable(
            GradientDrawable.Orientation.TOP_BOTTOM,
            intArrayOf(
                generatedLightColor,
                Color.parseColor("#00FFFFFF")
            )
        )

        backgroundProtection.background = gradientDrawable
    }

    private fun setCollapsingToolbar(titleString: String) {
        appbar.addOnOffsetChangedListener(object : OnOffsetChangedListener {
            var isShow = true
            var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingLayout.title = titleString
                    isShow = true
                } else if (isShow) {
                    collapsingLayout.title =
                        " " // there should a space between double quote otherwise it wont work
                    isShow = false
                }
            }
        })
    }

    companion object {

        private const val ARG_ALBUM_ID = "album_id"
        private const val ARG_ARTIST_NAME = "artist_name"
        private const val ARG_ALBUM_NAME = "album_name"
        private const val ARG_ALBUM_IMAGE = "album_image"
        private const val lightMutedColor = 0xFFAAAAAA

        fun newInstance(albumId: String, artistName: String, albumName: String, albumUrl: String) =
            AlbumTracksFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ALBUM_ID, albumId)
                    putString(ARG_ARTIST_NAME, artistName)
                    putString(ARG_ALBUM_NAME, albumName)
                    putString(ARG_ALBUM_IMAGE, albumUrl)
                }
            }
    }
}