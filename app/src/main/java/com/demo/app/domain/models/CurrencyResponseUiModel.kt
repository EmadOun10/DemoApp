package com.demo.app.domain.models

import com.demo.app.data.responses.CurrenciesResponseRemote


data class CurrencyResponseUiModel(
    val symbol: String?,
    val priceChange: String?,
    val priceChangePercent: String?,
    val weightedAvgPrice: String?,
    val prevClosePrice: String?,
    val lastPrice: String?,
    val lastQty: String?,
    val askPrice: String?,
    val openPrice: String?,
)

fun CurrenciesResponseRemote.mapToDemoResponseUiModel(): CurrencyResponseUiModel =
    CurrencyResponseUiModel(
        symbol = symbol,
        priceChange = priceChange,
        priceChangePercent = priceChangePercent,
        weightedAvgPrice = weightedAvgPrice,
        prevClosePrice = prevClosePrice,
        lastPrice = lastPrice,
        lastQty = lastQty,
        askPrice = askPrice,
        openPrice = openPrice
    )
