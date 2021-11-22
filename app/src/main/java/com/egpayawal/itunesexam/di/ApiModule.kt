package com.egpayawal.itunesexam.di

import com.egpayawal.itunesexam.api.service.ITunesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by Eraño Payawal on 11/22/21.
 * hunterxer31@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Singleton
    @Provides
    fun itunesApi(retrofit: Retrofit): ITunesApi = retrofit.create(ITunesApi::class.java)

}