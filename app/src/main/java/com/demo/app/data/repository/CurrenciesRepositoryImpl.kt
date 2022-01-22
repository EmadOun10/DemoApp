package com.demo.app.data.repository

import com.demo.app.data.apis.CurrenciesApis
import com.demo.app.domain.models.CurrencyResponseUiModel
import com.demo.app.domain.models.mapToDemoResponseUiModel
import com.demo.app.domain.repository.CurrenciesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CurrenciesRepositoryImpl @Inject constructor(
    private val currenciesAPI: CurrenciesApis
) : CurrenciesRepository {

    override suspend fun getCurrencies(): Flow<List<CurrencyResponseUiModel>> {
        return flow {
            val response = currenciesAPI.getCurrencies()
            if (response.isSuccessful) {
                response.body()?.map { it.mapToDemoResponseUiModel() }?.let { emit(it) }
            } else {
                error(response)
            }
        }
    }
}