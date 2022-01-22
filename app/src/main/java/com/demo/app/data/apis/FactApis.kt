package com.demo.app.data.apis

import com.demo.app.data.responses.FactResponseRemote
import retrofit2.Response
import retrofit2.http.GET


interface FactApis {
  @GET("fact")
  suspend fun getFact(): Response<FactResponseRemote>
}