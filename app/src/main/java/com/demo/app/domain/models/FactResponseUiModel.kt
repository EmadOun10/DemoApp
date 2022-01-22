package com.demo.app.domain.models

import com.demo.app.data.responses.FactResponseRemote


data class FactResponseUiModel(
    val fact: String?,
    val length: String?
)

fun FactResponseRemote.mapToFactResponseUiModel(): FactResponseUiModel =
    FactResponseUiModel(
        fact = fact,
        length = length
    )
