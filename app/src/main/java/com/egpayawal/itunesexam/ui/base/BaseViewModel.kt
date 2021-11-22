package com.egpayawal.itunesexam.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.egpayawal.itunesexam.utils.SingleEvent
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
open class BaseViewModel : ViewModel() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    private val _error = MutableLiveData<SingleEvent<Throwable>>()
    val error: LiveData<SingleEvent<Throwable>> = _error

    private val _errorMessage = MutableLiveData<SingleEvent<String>>()
    val errorMessage: LiveData<SingleEvent<String>> = _errorMessage

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    protected fun onError(throwable: Throwable) {
        Timber.e(throwable)
        _error.value = SingleEvent(throwable)
    }

    protected fun onError(message: String) {
        Timber.e(message)
        _errorMessage.value = SingleEvent(message)
    }

}