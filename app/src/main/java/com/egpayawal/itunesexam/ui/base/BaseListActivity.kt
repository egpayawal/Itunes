package com.egpayawal.itunesexam.ui.base

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.egpayawal.itunesexam.sealedclass.ListItemView
import com.egpayawal.itunesexam.ui.ext.startOrStopShimmer
import com.facebook.shimmer.ShimmerFrameLayout

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
abstract class BaseListActivity<Item, Param>(
    private val paramLoadedOnInit: Boolean
) : BaseActivity() {

    data class Views(
        val refreshLayout: SwipeRefreshLayout,
        val recyclerView: RecyclerView,
        val shimmerLoading: ShimmerFrameLayout,
        val emptyState: View,
        val containedEndOfList: View?
    )

    abstract fun getViews(): Views

    abstract fun getVM(): BaseListViewModel<Item, Param>

    abstract fun getParam(): Param

    abstract fun initialize(param: Param, items: LiveData<List<ListItemView<Item>>>)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val views = getViews()
        val viewModel = getVM()

        views.refreshLayout.setOnRefreshListener {
            viewModel.fetchItems(true)
        }

        views.recyclerView.layoutManager = LinearLayoutManager(this)
        views.recyclerView.itemAnimator = null
        views.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!recyclerView.canScrollVertically(1)) {
                    viewModel.fetchItems(false)
                }
            }
        })

        viewModel.isLoading.observe(this) { isLoading ->
            views.refreshLayout.isRefreshing = isLoading
        }
        viewModel.showLoadingShimmer.observe(this) { isLoading ->
            views.shimmerLoading.isVisible = isLoading
            views.shimmerLoading.startOrStopShimmer(isLoading)
        }
        viewModel.showEmptyState.observe(this) { isEmpty ->
            views.emptyState.isVisible = isEmpty
        }

        viewModel.observeErrors()

        if (paramLoadedOnInit) {
            val param = getParam()
            viewModel.getItems(param)
            if (!viewModel.refreshItemsOnGet) {
                // manually call if not automatic
                viewModel.fetchItems(true)
            }
            initialize(param, viewModel.items)
        }
    }

}