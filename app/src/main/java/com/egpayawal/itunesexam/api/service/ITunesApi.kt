package com.egpayawal.itunesexam.api.service

import com.egpayawal.itunesexam.api.response.GenericApiResponse
import com.egpayawal.itunesexam.model.Movie
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
interface ITunesApi {

    @GET("search?term=star&amp;country=au&amp;media=movie&amp;all")
    fun getMovies(): Single<GenericApiResponse<List<Movie>>>

}