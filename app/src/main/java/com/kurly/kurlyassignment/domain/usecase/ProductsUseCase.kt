package com.kurly.kurlyassignment.domain.usecase

import com.kurly.kurlyassignment.data.remote.model.ProductDto
import com.kurly.kurlyassignment.domain.repository.SectionRepository
import javax.inject.Inject

class ProductsUseCase @Inject constructor(
    private val sectionRepository: SectionRepository
) {
    suspend operator fun invoke(sectionId: Int): List<ProductDto> = sectionRepository.products(sectionId)
}