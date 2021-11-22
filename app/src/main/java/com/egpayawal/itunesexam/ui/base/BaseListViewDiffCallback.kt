package com.egpayawal.itunesexam.ui.base

import androidx.recyclerview.widget.DiffUtil
import com.egpayawal.itunesexam.sealedclass.ListItemView

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
abstract class BaseListViewDiffCallback<T> : DiffUtil.ItemCallback<ListItemView<T>>() {

    abstract fun areIdSame(oldItem: T, newItem: T): Boolean

    abstract fun areContentsSame(oldItem: T, newItem: T): Boolean

    override fun areItemsTheSame(oldItem: ListItemView<T>, newItem: ListItemView<T>): Boolean = when {
        oldItem is ListItemView.ItemView && newItem is ListItemView.ItemView -> areIdSame(oldItem.item, newItem.item)
        oldItem is ListItemView.LoadingView && newItem is ListItemView.LoadingView -> true
        oldItem is ListItemView.EndView && newItem is ListItemView.EndView -> true
        else -> false
    }

    override fun areContentsTheSame(oldItem: ListItemView<T>, newItem: ListItemView<T>): Boolean = when {
        oldItem is ListItemView.ItemView && newItem is ListItemView.ItemView -> areContentsSame(oldItem.item, newItem.item)
        oldItem is ListItemView.LoadingView && newItem is ListItemView.LoadingView -> true
        oldItem is ListItemView.EndView && newItem is ListItemView.EndView -> true
        else -> false
    }

}