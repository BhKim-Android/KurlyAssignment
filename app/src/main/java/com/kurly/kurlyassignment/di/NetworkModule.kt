package com.kurly.kurlyassignment.di

import android.content.Context
import com.kurly.android.mockserver.MockInterceptor
import com.kurly.kurlyassignment.data.remote.api.SectionApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    fun provideMockInterceptor(@ApplicationContext context: Context): Interceptor =
        MockInterceptor(context)

    @Provides
    fun provideOkHttpClient(mockInterceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(mockInterceptor)
            .build()

    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://kurly.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    fun provideSectionApi(retrofit: Retrofit): SectionApiService =
        retrofit.create(SectionApiService::class.java)
}