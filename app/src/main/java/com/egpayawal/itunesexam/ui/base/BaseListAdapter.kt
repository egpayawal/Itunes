package com.egpayawal.itunesexam.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.egpayawal.itunesexam.sealedclass.ListItemView
import com.egpayawal.itunesexam.ui.ext.layoutInflater

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
abstract class BaseListAdapter<T, out VH : BaseListAdapter.BaseListViewHolder<T>>(
    @LayoutRes private val loadingLayoutRes: Int?,
    @LayoutRes private val endViewLayoutRes: Int?,
    diffCallback: BaseListViewDiffCallback<T>
) : ListAdapter<ListItemView<T>, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private const val VIEW_ITEM = 1
        private const val VIEW_LOADING = 2
        private const val VIEW_END = 3
    }

    abstract fun inflateItemView(parent: ViewGroup): RecyclerView.ViewHolder

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is ListItemView.ItemView -> VIEW_ITEM
        ListItemView.LoadingView -> VIEW_LOADING
        ListItemView.EndView -> VIEW_END
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder = when (viewType) {
        VIEW_ITEM -> inflateItemView(parent)
        VIEW_LOADING -> SimpleViewHolder(
            parent.layoutInflater.inflate(
                loadingLayoutRes ?: throw IllegalStateException("VIEW_LOADING is used but null"),
                parent,
                false
            )
        )
        VIEW_END -> SimpleViewHolder(
            parent.layoutInflater.inflate(
                endViewLayoutRes ?: throw IllegalStateException("VIEW_END is used but null"),
                parent,
                false
            )
        )
        else -> throw IllegalStateException("unknown viewType: $viewType")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (getItem(position) as? ListItemView.ItemView)?.let {
            (holder as VH).bind(it.item)
        }
    }

    abstract class BaseListViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {

        abstract fun bind(item: T)

    }

    class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view)

}