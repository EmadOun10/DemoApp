package com.demo.app.domain.interactor

import com.demo.app.domain.models.FactResponseUiModel
import com.demo.app.domain.repository.FactRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFactUseCase @Inject constructor(
    private val factRepository: FactRepository
) {
    suspend fun invoke(): Flow<FactResponseUiModel> = factRepository.getFact()
}