package com.kurly.kurlyassignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.kurly.kurlyassignment.common.toWon
import com.kurly.kurlyassignment.domain.model.Product
import com.kurly.kurlyassignment.domain.model.SectionWithProducts
import com.kurly.kurlyassignment.domain.usecase.ProductsUseCase
import com.kurly.kurlyassignment.domain.usecase.SectionsUseCase
import com.kurly.kurlyassignment.utils.DiscountUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    sectionsUseCase: SectionsUseCase,
    private val productsUseCase: ProductsUseCase
) : ViewModel() {
    val sectionsFlow = sectionsUseCase()
    val sectionsWithProductsFlow: Flow<PagingData<SectionWithProducts>> =
        sectionsFlow.map { pagingData ->
            pagingData.map { section ->
                val products = productsUseCase(section.id).map {
                    Product(
                        id = it.id,
                        name = it.name,
                        image = it.image,
                        discount = DiscountUtils.calculateDiscount(
                            originalPrice = it.originalPrice,
                            discountedPrice = it.discountedPrice
                        ),
                        originalPrice = it.originalPrice.toWon(),
                        discountedPrice = it.discountedPrice?.toWon(),
                        isSoldOut = it.isSoldOut
                    )
                }
                SectionWithProducts(section, products)
            }
        }.cachedIn(viewModelScope)
}