package com.demo.app.data.di

import com.demo.app.data.apis.CurrenciesApis
import com.demo.app.data.apis.FactApis
import com.demo.app.data.di.DaggerConstants.CURRENCY_URL
import com.demo.app.data.di.DaggerConstants.FACT_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module(includes = [RetrofitModule::class])
@InstallIn(SingletonComponent::class)
class APIModule {

    @Singleton
    @Provides
    fun provideCurrenciesApi(@Named(CURRENCY_URL) retrofit: Retrofit): CurrenciesApis =
        retrofit.create(CurrenciesApis::class.java)

    @Singleton
    @Provides
    fun provideFactApi(@Named(FACT_URL) retrofit: Retrofit): FactApis =
        retrofit.create(FactApis::class.java)
}