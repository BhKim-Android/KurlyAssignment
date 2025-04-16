package com.kurly.kurlyassignment.domain.repository

import androidx.paging.PagingData
import com.kurly.kurlyassignment.data.model.ProductDto
import com.kurly.kurlyassignment.domain.model.Section
import kotlinx.coroutines.flow.Flow

interface SectionRepository {
    fun sections(): Flow<PagingData<Section>>
    suspend fun products(sectionId: Int): List<ProductDto>
}