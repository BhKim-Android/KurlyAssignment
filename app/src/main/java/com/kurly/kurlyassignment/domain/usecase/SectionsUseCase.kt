package com.kurly.kurlyassignment.domain.usecase

import androidx.paging.PagingData
import com.kurly.kurlyassignment.domain.model.Section
import com.kurly.kurlyassignment.domain.repository.SectionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SectionsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository
) {

    operator fun invoke(): Flow<PagingData<Section>> = sectionRepository.sections()
}