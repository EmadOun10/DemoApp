package com.demo.app.data.repository

import com.demo.app.data.apis.FactApis
import com.demo.app.domain.models.CurrencyResponseUiModel
import com.demo.app.domain.models.FactResponseUiModel
import com.demo.app.domain.models.mapToFactResponseUiModel
import com.demo.app.domain.repository.CurrenciesRepository
import com.demo.app.domain.repository.FactRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class FactRepositoryImpl @Inject constructor(private val factApis: FactApis) : FactRepository {

  override suspend fun getFact(): Flow<FactResponseUiModel> {
    return flow {
      val response = factApis.getFact()
      if (response.isSuccessful) {
        response.body()?.mapToFactResponseUiModel()?.let { emit(it) }
      } else {
        error(response)
      }
    }
  }
}