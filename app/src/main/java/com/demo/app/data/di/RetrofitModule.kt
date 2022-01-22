package com.demo.app.data.di

import com.demo.app.BuildConfig
import com.demo.app.data.di.DaggerConstants.CURRENCY_URL
import com.demo.app.data.di.DaggerConstants.FACT_URL
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

  @Singleton
  @Provides fun provideGson(): Gson = Gson()

  @Singleton
  @Provides
  @Named(CURRENCY_URL)
  fun provideCurrencyRetrofit(
    client: OkHttpClient,
    gson: Gson,
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL_FIRST)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()

  @Singleton
  @Provides
  @Named(FACT_URL)
  fun provideFactRetrofit(
    client: OkHttpClient,
    gson: Gson,
  ): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL_SECOND)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create(gson))
    .client(client)
    .build()
}