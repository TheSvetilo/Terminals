package com.vbogd.terminals.di.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.vbogd.terminals.data.terminalRepository.remote.TerminalApi
import com.vbogd.terminals.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.TERMINALS_API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): TerminalApi {
        return retrofit.create(TerminalApi::class.java)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

}