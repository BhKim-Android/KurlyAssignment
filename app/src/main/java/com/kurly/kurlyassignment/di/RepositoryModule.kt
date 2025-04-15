package com.kurly.kurlyassignment.di

import com.kurly.kurlyassignment.data.repository.SectionRepositoryImpl
import com.kurly.kurlyassignment.domain.repository.SectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindProductRepository(
        impl: SectionRepositoryImpl
    ): SectionRepository
}