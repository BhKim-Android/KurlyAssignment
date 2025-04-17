package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kurly.kurlyassignment.domain.model.Product
import com.kurly.kurlyassignment.presentation.viewmodel.MainViewmodel

@Composable
fun GridSection(
    products: List<Product>,
    viewModel: MainViewmodel = hiltViewModel()
) {
    val maxHeight by viewModel.maxItemHeight.collectAsState()
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxWidth()
            .height(with(LocalDensity.current) { maxHeight.toDp() * 2 }),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(products) { product ->
            SmallItemProduct(
                product = product,
                onHeightMeasured = { height -> viewModel.updateItemHeight(height) })
        }
    }
}