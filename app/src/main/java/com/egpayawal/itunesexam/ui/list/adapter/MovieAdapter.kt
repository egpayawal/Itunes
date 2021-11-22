package com.egpayawal.itunesexam.ui.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.egpayawal.itunesexam.databinding.ItemMovieBinding
import com.egpayawal.itunesexam.model.Movie
import com.egpayawal.itunesexam.ui.base.BaseListAdapter
import com.egpayawal.itunesexam.ui.diffutil.MovieItemDiff
import com.egpayawal.itunesexam.ui.ext.FIXED_400
import com.egpayawal.itunesexam.ui.ext.layoutInflater
import com.egpayawal.itunesexam.ui.ext.loadImage

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
class MovieAdapter(
    private val onViewDetailsClicked: (Movie) -> Unit
) : BaseListAdapter<Movie, MovieAdapter.ViewHolder>(
    loadingLayoutRes = null,
    endViewLayoutRes = null,
    diffCallback = MovieItemDiff
) {

    override fun inflateItemView(parent: ViewGroup): RecyclerView.ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                parent.layoutInflater,
                parent,
                false
            ),
            onViewDetailsClicked
        )
    }

    class ViewHolder(
        private val binding: ItemMovieBinding,
        private val onViewDetailsClicked: (Movie) -> Unit
    ) : BaseListViewHolder<Movie>(binding.root) {

        override fun bind(item: Movie) {
            binding.root.setOnClickListener { onViewDetailsClicked(item) }
            binding.imgBanner.loadImage(item.artworkUrl100, FIXED_400)
            binding.txtTitle.text = item.displayTitle
            binding.txtPrice.text = item.displayPrice
            binding.txtGenre.text = item.displayGenre
        }

    }

}