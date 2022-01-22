package com.demo.app.data.di

import androidx.viewbinding.BuildConfig
import com.demo.app.data.utils.RequestInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit.SECONDS
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class OKHttpClientModule {

  @Singleton
  @Provides fun provideOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    networkInterceptor: RequestInterceptor
  ): OkHttpClient = Builder().connectTimeout(20, SECONDS)
    .readTimeout(30, SECONDS)
    .writeTimeout(20, SECONDS)
    .retryOnConnectionFailure(true)
    .addInterceptor(loggingInterceptor)
    .addInterceptor(networkInterceptor)
    .build()

  @Singleton
  @Provides fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
    HttpLoggingInterceptor().apply { setLevel(if (BuildConfig.DEBUG) Level.BASIC else Level.NONE) }

  @Provides
  fun provideRequestInterceptor(): RequestInterceptor {
    return RequestInterceptor()
  }
}
