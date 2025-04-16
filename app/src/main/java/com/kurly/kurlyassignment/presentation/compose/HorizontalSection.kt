package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import com.kurly.kurlyassignment.domain.model.Product
import com.kurly.kurlyassignment.presentation.viewmodel.MainViewmodel

@Composable
fun HorizontalSection(
    products: List<Product>,
    viewModel: MainViewmodel = hiltViewModel()
) {
    val maxHeight by viewModel.maxItemHeight.collectAsState()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(with(LocalDensity.current) { maxHeight.toDp() }),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(products) { product ->
            SmallItemProduct(
                product = product,
                onHeightMeasured = { height -> viewModel.updateItemHeight(height) })
        }
    }
}

@Preview
@Composable
fun HorizontalSectionPreview() {

}