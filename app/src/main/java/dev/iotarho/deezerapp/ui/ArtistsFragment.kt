package dev.iotarho.deezerapp.ui

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.text.InputType
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.ProgressBar
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.iotarho.deezerapp.R
import dev.iotarho.deezerapp.models.SearchResult
import org.koin.android.viewmodel.ext.android.viewModel


class ArtistsFragment : Fragment() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView

    private var queryString: String? = null
    private val resultsViewModel: ResultsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        arguments?.let {
            queryString = it.getString(ARG_QUERY)
            Log.d("ArtistFragment", "ACTION_SEARCH, query is : $queryString")
            queryString?.let { query -> resultsViewModel.setQuery(query) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_artists, container, false)
        recyclerView = view.findViewById(R.id.list)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBar)

        resultsViewModel.query.observe(viewLifecycleOwner) { query ->
            Log.d("ArtistFragment", "query is : $query")
            queryString = query
        }

        resultsViewModel.loading.observe(viewLifecycleOwner) {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        resultsViewModel.results.observe(viewLifecycleOwner) {
            setupResults(it)
        }

        if (queryString == null) {
            queryString = "Eminem"
        }
        resultsViewModel.setQuery(queryString!!)

        return view
    }

    private fun setupResults(searchResult: SearchResult) {
        Log.d("ArtistFragment", "search results is : $searchResult")
        // Set the adapter
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context)
            adapter = MyItemRecyclerViewAdapter(searchResult.data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)

        // Set the SearchView
        val searchManager =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setIconifiedByDefault(false) // Do not iconify the widget, expand it by default
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.isSubmitButtonEnabled = false
        searchView.inputType = InputType.TYPE_TEXT_FLAG_CAP_WORDS

        if (queryString.isNullOrEmpty().not()) {
            searchView.onActionViewExpanded()
            searchView.setQuery(queryString, true)
            queryString?.let { resultsViewModel.setQuery(it) }
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d("ArtistFragment", "temp, search query is : $query")
                query?.let { doSearch(searchView, it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d("ArtistFragment", "temp, search newText is : $newText")
                newText?.let { doSearch(searchView, it) }
                return false
            }
        })

        searchView.clearFocus()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_search) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun doSearch(v: View, query: String) {
        dismissKeyboard(v.windowToken) // Dismiss keyboard
        resultsViewModel.setQuery(query)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }

    companion object {

        const val ARG_QUERY = "arg_query"

        fun newInstance(query: String) =
            ArtistsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_QUERY, query)
                }
            }
    }
}