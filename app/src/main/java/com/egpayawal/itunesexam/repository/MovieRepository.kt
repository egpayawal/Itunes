package com.egpayawal.itunesexam.repository

import com.egpayawal.itunesexam.api.service.ITunesApi
import com.egpayawal.itunesexam.db.MovieDao
import com.egpayawal.itunesexam.model.Movie
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
class MovieRepository @Inject constructor(
    private val dao: MovieDao,
    private val api: ITunesApi
) {

    /**
     * get local data
     */
    fun getMovies(): Flowable<List<Movie>> {
        return dao.getAll()
    }

    /**
     * fetch data from remote
     */
    fun fetchMovies(deleteOldData: Boolean): Single<Boolean> {
        return api.getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .flatMap { response ->
                dao.saveAll(
                    obj = response.results?.map { it } ?: emptyList(),
                    deleteOldData = deleteOldData
                ).map { false }
            }
            .observeOn(AndroidSchedulers.mainThread())
    }

    /**
     * @param trackId Long
     * Get movie by trackId
     */
    fun getMovie(trackId: Long): Flowable<Movie> {
        return dao.get(trackId)
    }

}