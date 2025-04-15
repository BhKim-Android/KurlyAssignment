package com.kurly.kurlyassignment.domain.repository

import androidx.paging.PagingData
import com.kurly.kurlyassignment.domain.model.Section
import kotlinx.coroutines.flow.Flow

interface SectionRepository {
    fun sections(): Flow<PagingData<Section>>
}