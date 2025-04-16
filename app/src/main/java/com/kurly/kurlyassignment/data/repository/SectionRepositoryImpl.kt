package com.kurly.kurlyassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kurly.kurlyassignment.data.model.ProductDto
import com.kurly.kurlyassignment.data.source.SectionPagingSource
import com.kurly.kurlyassignment.data.source.SectionRemoteDataSource
import com.kurly.kurlyassignment.domain.model.Section
import com.kurly.kurlyassignment.domain.repository.SectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SectionRepositoryImpl @Inject constructor(
    private val sectionRemoteDataSource: SectionRemoteDataSource
) : SectionRepository {
    override fun sections(): Flow<PagingData<Section>> =
        Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory = { SectionPagingSource(sectionRemoteDataSource) }
        ).flow

    override suspend fun products(sectionId: Int): List<ProductDto> =
        sectionRemoteDataSource.products(sectionId).data
}