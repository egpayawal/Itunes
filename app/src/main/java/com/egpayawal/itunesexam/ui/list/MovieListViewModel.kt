package com.egpayawal.itunesexam.ui.list

import com.egpayawal.itunesexam.model.Movie
import com.egpayawal.itunesexam.repository.MovieRepository
import com.egpayawal.itunesexam.ui.base.BaseListViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val repository: MovieRepository
) : BaseListViewModel<Movie, Boolean>(hasEndOfPostView = false) {

    override fun queryLocal(param: Boolean): Flowable<List<Movie>> {
        return repository.getMovies()
    }

    override fun queryRemote(param: Boolean, isPullToRefresh: Boolean): Single<Boolean> {
        return repository.fetchMovies(deleteOldData = isPullToRefresh)
    }


}