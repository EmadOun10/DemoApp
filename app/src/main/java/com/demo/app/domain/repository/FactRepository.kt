package com.demo.app.domain.repository

import com.demo.app.domain.models.FactResponseUiModel
import kotlinx.coroutines.flow.Flow

interface FactRepository {
  suspend fun getFact(): Flow<FactResponseUiModel>
}