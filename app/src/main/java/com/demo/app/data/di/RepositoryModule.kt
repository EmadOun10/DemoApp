package com.demo.app.data.di

import com.demo.app.data.repository.CurrenciesRepositoryImpl
import com.demo.app.data.repository.FactRepositoryImpl
import com.demo.app.domain.repository.CurrenciesRepository
import com.demo.app.domain.repository.FactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindCurrenciesRepository(
        currenciesRepositoryImpl: CurrenciesRepositoryImpl): CurrenciesRepository

    @Binds
    abstract fun bindFactRepository(
        factRepositoryImpl: FactRepositoryImpl): FactRepository
}