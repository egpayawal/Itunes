package com.egpayawal.itunesexam.ui.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.egpayawal.itunesexam.R
import com.egpayawal.itunesexam.databinding.ActivityDetailsBinding
import com.egpayawal.itunesexam.model.Movie
import com.egpayawal.itunesexam.ui.base.BaseActivity
import com.egpayawal.itunesexam.ui.ext.*
import com.egpayawal.itunesexam.utils.Constants
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
@AndroidEntryPoint
class MovieDetailsActivity : BaseActivity() {

    private val binding by viewBinding(ActivityDetailsBinding::inflate)
    private val viewModel by viewModels<MovieDetailViewModel>()

    private var trackId: Long? = null

    companion object {

        fun newInstance(context: Context, trackId: Long): Intent =
            Intent(context, MovieDetailsActivity::class.java).apply {
                putExtra(Constants.TRACK_ID, trackId)
            }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        trackId = intent?.getLongExtra(Constants.TRACK_ID, -1L)

        observables()
    }

    private fun observables() {
        trackId?.let { viewModel.subscribeMovie(it) }
        viewModel.movie.observe(this@MovieDetailsActivity, ::setMovie)
        viewModel.observeErrors()
    }

    private fun setMovie(movie: Movie?) {
        movie?.let {
            binding.imgBanner.loadImage(it.artworkUrl100, FIXED_800)
            binding.txtTitle.text = it.displayTitle
            val releaseDate = it.releaseDate
                ?.toDate("yyyy-MM-dd'T'HH:mm:ssZ")
                ?.toString("MMM dd, yyyy")
            binding.txtReleaseDate.text = getString(R.string.release_date, releaseDate)
            binding.txtPrice.text = it.displayPrice
            binding.txtGenre.text = it.displayGenre
            binding.txtSynopsis.text = it.displaySynopsis ?: getString(R.string.no_description)
            binding.txtRating.text = it.contentAdvisoryRating
            binding.txtRating.isVisible = !it.contentAdvisoryRating.isNullOrEmpty()
        }
    }

    override fun onBackPressed() {
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}