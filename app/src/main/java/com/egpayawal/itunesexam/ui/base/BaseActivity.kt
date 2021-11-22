package com.egpayawal.itunesexam.ui.base

import androidx.appcompat.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {

    protected val disposeBag: CompositeDisposable by lazy { CompositeDisposable() }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.dispose()
    }

    protected fun BaseViewModel.observeErrors() {
        this.error.observe(this@BaseActivity) {
            it.getContentIfNotHandled()?.let { error ->
                //ErrorHandler.handleError(this@BaseActivity, error)
            }
        }
    }

}