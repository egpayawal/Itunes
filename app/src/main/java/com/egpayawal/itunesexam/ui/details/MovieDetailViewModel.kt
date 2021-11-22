package com.egpayawal.itunesexam.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.egpayawal.itunesexam.model.Movie
import com.egpayawal.itunesexam.repository.MovieRepository
import com.egpayawal.itunesexam.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseViewModel() {

    private val _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie> = _movie

    fun subscribeMovie(trackId: Long) {
        repository.getMovie(trackId)
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _movie.value = it
            }, this::onError)
            .addTo(disposable)
    }

}