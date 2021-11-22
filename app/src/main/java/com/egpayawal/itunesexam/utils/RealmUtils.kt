package com.egpayawal.itunesexam.utils

import android.os.Looper
import io.reactivex.Flowable
import io.reactivex.Single
import io.realm.*
import timber.log.Timber

fun <T : RealmObject> T.unmanaged(): T = when {
    this.isManaged -> this.realm.copyFromRealm(this)
    else -> this
}

fun <T : RealmObject> Iterable<T>.unmanaged(): List<T> = this.map { it.unmanaged() }

fun <T : RealmObject> RealmQuery<T>.findFirstFlowable(): Flowable<T> = this.findAllAsync()
    .asFlowable()
    .filter { it.isNotEmpty() }
    .map { realm.copyFromRealm(it.first()!!) } // safe !! as list already filtered to be not empty on the chain

object RealmUtils {

    fun <T : Any> rxTransaction(
        transaction: (Realm) -> T
    ): Single<T> = Single.create { emitter ->
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (!emitter.isDisposed) emitter.onError(IllegalStateException("rxTransaction must not be on main thread!!!"))
            } else {
                Realm.getDefaultInstance()
                    .use { realm ->
                        var result: T? = null
                        Timber.d("rxTransaction thread: ${Thread.currentThread().name}")
                        realm.executeTransaction {
                            result = transaction.invoke(it)
                        }
                        if (!emitter.isDisposed) emitter.onSuccess(result!!)
                    }
            }
        } catch (e: Throwable) {
            Timber.e(e)
            if (!emitter.isDisposed) emitter.onError(e)
        }
    }

    fun <T : Any> rxQuery(
        query: (Realm) -> T
    ): Single<T> = Single.create { emitter ->
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                if (!emitter.isDisposed) emitter.onError(IllegalStateException("rxTransaction must not be on main thread!!!"))
            } else {
                Realm.getDefaultInstance()
                    .use { realm ->
                        val result = query(realm)
                        if (!emitter.isDisposed) emitter.onSuccess(result)
                    }
            }
        } catch (e: Throwable) {
            Timber.e(e)
            if (!emitter.isDisposed) emitter.onError(e)
        }
    }

}