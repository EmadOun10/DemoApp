package com.demo.app.domain.interactor

import com.demo.app.domain.models.CurrencyResponseUiModel
import com.demo.app.domain.repository.CurrenciesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCurrenciesUseCase @Inject constructor(
  private val demoRepository: CurrenciesRepository
) {
  suspend fun invoke(): Flow<List<CurrencyResponseUiModel>> = demoRepository.getCurrencies()
}