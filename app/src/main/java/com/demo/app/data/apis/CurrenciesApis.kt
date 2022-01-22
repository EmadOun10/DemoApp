package com.demo.app.data.apis

import com.demo.app.data.responses.CurrenciesResponseRemote
import retrofit2.Response
import retrofit2.http.GET

interface CurrenciesApis {
    @GET("api/v3/ticker/24hr")
    suspend fun getCurrencies(): Response<List<CurrenciesResponseRemote>>
}