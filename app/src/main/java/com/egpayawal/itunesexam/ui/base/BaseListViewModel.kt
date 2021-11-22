package com.egpayawal.itunesexam.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egpayawal.itunesexam.sealedclass.ListItemView
import com.egpayawal.itunesexam.ui.ext.LiveDataExt
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import timber.log.Timber

/**
 * @property Item is the item
 * @property Param is the parameter
 * @param hasEndOfPostView true if there is a footer view signaling end of the list, false otherwise.
 * @param refreshItemsOnGet set false if fetching remote is not required on getting items e.g. search string locally only. Default is true
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
@Suppress("NULLABLE_TYPE_PARAMETER_AGAINST_NOT_NULL_TYPE_PARAMETER")
abstract class BaseListViewModel<Item, Param>(
    hasEndOfPostView: Boolean,
    val refreshItemsOnGet: Boolean = true
) : BaseViewModel() {

    private val paramLive = MutableLiveData<Param>()
    private val _items = MutableLiveData<List<Item>>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _enableLoadMore = MutableLiveData<Boolean>()

    val items: LiveData<List<ListItemView<Item>>> = LiveDataExt.generateLiveListItems(
        _enableLoadMore = _enableLoadMore,
        _items = _items,
        hasEndOfPost = hasEndOfPostView
    )
    val isLoading: LiveData<Boolean> = _isLoading
    val showEmptyState: LiveData<Boolean> = LiveDataExt.generateShowEmptyState(_isLoading, _items)
    val showLoadingShimmer: LiveData<Boolean> = LiveDataExt.generateShowLoading(_isLoading, _items)

    private val getAction = PublishSubject.create<Param>()

    /**
     * @return flowable list of items
     */
    protected abstract fun queryLocal(param: Param): Flowable<List<Item>>

    /**
     * @return single true if enable load more, false otherwise.
     */
    protected abstract fun queryRemote(param: Param, isPullToRefresh: Boolean): Single<Boolean>

    init {
        getAction
            .distinctUntilChanged()
            .toFlowable(BackpressureStrategy.LATEST)
            .doOnNext { if (refreshItemsOnGet) fetchItems(true) }
            .switchMap { queryLocal(it) }
            .subscribe({
                _items.value = it
            }, Timber::e)
            .addTo(disposable)
    }

    fun getItems(param: Param) {
        paramLive.value = param!!
        getAction.onNext(param)
    }

    fun fetchItems(isPullToRefresh: Boolean) {
        if (!isPullToRefresh && _enableLoadMore.value == false) return // ignore
        val param = paramLive.value
            ?: throw IllegalStateException("BaseListViewModel.getItems(param) not yet called")
        queryRemote(param, isPullToRefresh)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { if (isPullToRefresh) _isLoading.value = true }
            .doFinally { _isLoading.value = false }
            .subscribe({
                _enableLoadMore.value = it
            }, {
                _enableLoadMore.value = false
                onError(it)
            })
            .addTo(disposable)
    }

}