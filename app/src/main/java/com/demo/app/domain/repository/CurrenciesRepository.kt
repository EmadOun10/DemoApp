package com.demo.app.domain.repository

import com.demo.app.domain.models.CurrencyResponseUiModel
import kotlinx.coroutines.flow.Flow

interface CurrenciesRepository {
  suspend fun getCurrencies(): Flow<List<CurrencyResponseUiModel>>
}