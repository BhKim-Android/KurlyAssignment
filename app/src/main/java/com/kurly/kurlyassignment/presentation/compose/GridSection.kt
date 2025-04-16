package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kurly.kurlyassignment.domain.model.Product

@Composable
fun GridSection(products: List<Product>) {
//    Grid(
//        cells = GridCells.Fixed(3), // 열 개수 3개
//        modifier = Modifier.fillMaxWidth()
//    ) {
//        products.forEach { product ->
//            SmallItemProduct(product = product)
//        }
//    }
}

@Preview
@Composable
fun GridSectionPreview() {

}