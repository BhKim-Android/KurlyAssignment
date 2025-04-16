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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewmodel @Inject constructor(
    sectionsUseCase: SectionsUseCase,
    private val productsUseCase: ProductsUseCase
) : ViewModel() {
    private val _maxItemHeight = MutableStateFlow(0)
    val maxItemHeight: StateFlow<Int> = _maxItemHeight

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


    fun updateItemHeight(height: Int) {
        _maxItemHeight.update { current -> maxOf(current, height) }
    }
}