package com.egpayawal.itunesexam.api.response

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
class GenericApiResponse<T> {

    var resultCount: Int= 0

    var results: T? = null

    companion object {
        fun <T> create(body: String): GenericApiResponse<T> {
            val collectionType = object : TypeToken<GenericApiResponse<T>>() {}.type
            return Gson().fromJson(body, collectionType)
        }
    }

}