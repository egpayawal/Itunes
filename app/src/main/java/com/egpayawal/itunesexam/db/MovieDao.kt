package com.egpayawal.itunesexam.db

import com.egpayawal.itunesexam.model.Movie
import com.egpayawal.itunesexam.utils.RealmUtils
import com.egpayawal.itunesexam.utils.findFirstFlowable
import com.egpayawal.itunesexam.utils.unmanaged
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmQuery
import io.realm.Sort
import io.realm.kotlin.where
import javax.inject.Inject

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
class MovieDao @Inject constructor() {

    fun getAll(): Flowable<List<Movie>> = Realm.getDefaultInstance()
        .filter()
        .sort(Movie::releaseDate.name, Sort.DESCENDING)
        .findAllAsync()
        .asFlowable()
        .map { it.unmanaged() }

    fun saveAll(obj: List<Movie>, deleteOldData: Boolean): Single<List<Movie>> =
        RealmUtils.rxTransaction { r ->
            if (deleteOldData) {
                r.filter()
                    .findAll()
                    .deleteAllFromRealm()
            }
            r.copyFromRealm(r.copyToRealmOrUpdate(obj))
        }

    fun get(trackId: Long): Flowable<Movie> = Realm.getDefaultInstance()
        .where<Movie>()
        .equalTo(Movie::trackId.name, trackId)
        .findFirstFlowable()

    private fun Realm.filter(): RealmQuery<Movie> = this.where<Movie>()

}