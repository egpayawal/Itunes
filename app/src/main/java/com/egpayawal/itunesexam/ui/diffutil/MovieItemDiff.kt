package com.egpayawal.itunesexam.ui.diffutil

import com.egpayawal.itunesexam.model.Movie
import com.egpayawal.itunesexam.ui.base.BaseListViewDiffCallback

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
object MovieItemDiff : BaseListViewDiffCallback<Movie>() {
    override fun areIdSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean = oldItem.trackId == newItem.trackId

    override fun areContentsSame(
        oldItem: Movie,
        newItem: Movie
    ): Boolean = oldItem == newItem
}