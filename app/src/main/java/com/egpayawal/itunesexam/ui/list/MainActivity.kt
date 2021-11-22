package com.egpayawal.itunesexam.ui.list

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.egpayawal.itunesexam.R
import com.egpayawal.itunesexam.databinding.ActivityMainBinding
import com.egpayawal.itunesexam.model.Movie
import com.egpayawal.itunesexam.sealedclass.ListItemView
import com.egpayawal.itunesexam.ui.base.BaseListActivity
import com.egpayawal.itunesexam.ui.base.BaseListViewModel
import com.egpayawal.itunesexam.ui.details.MovieDetailsActivity
import com.egpayawal.itunesexam.ui.ext.toString
import com.egpayawal.itunesexam.ui.ext.viewBinding
import com.egpayawal.itunesexam.ui.list.adapter.MovieAdapter
import com.egpayawal.itunesexam.utils.Constants
import com.egpayawal.itunesexam.utils.PreferenceHelper.get
import com.egpayawal.itunesexam.utils.PreferenceHelper.put
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseListActivity<Movie, Boolean>(paramLoadedOnInit = true) {

    private val binding by viewBinding(ActivityMainBinding::inflate)
    private val viewModel by viewModels<MovieListViewModel>()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun getViews(): Views {
        return Views(
            refreshLayout = binding.swipeRefresh,
            recyclerView = binding.rvMovies,
            shimmerLoading = binding.shimmerViewContainer,
            emptyState = binding.containerEmptyState.root,
            containedEndOfList = null
        )
    }

    override fun getVM(): BaseListViewModel<Movie, Boolean> {
        return viewModel
    }

    override fun getParam(): Boolean {
        return true
    }

    override fun initialize(param: Boolean, items: LiveData<List<ListItemView<Movie>>>) {
        binding.txtPreviousVisited.text =
            sharedPreferences.get(Constants.LAST_VISITED, getString(R.string.no_visit))
        val visit =
            "${getString(R.string.previous_visited)} ${Date().toString("MMM dd, yyyy hh:mm aa")}"
        sharedPreferences.put(Constants.LAST_VISITED, visit)

        val adapterMovie = MovieAdapter(onViewDetailsClicked = ::showViewDetails)
        binding.rvMovies.apply {
            val linearLayoutManager = GridLayoutManager(this@MainActivity, 2)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            layoutManager = linearLayoutManager
            adapter = adapterMovie
        }
        items.observe(this@MainActivity, adapterMovie::submitList)
    }

    private fun showViewDetails(item: Movie) {
        startActivity(
            MovieDetailsActivity.newInstance(
                context = this@MainActivity,
                trackId = item.trackId
            )
        )
    }
}