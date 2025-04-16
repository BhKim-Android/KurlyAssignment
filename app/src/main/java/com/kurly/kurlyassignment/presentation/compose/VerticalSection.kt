package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kurly.kurlyassignment.domain.model.Product

@Composable
fun VerticalSection(products: List<Product>) {
    Column(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        products.forEach { product ->
            LargeItemProduct(product)
        }
    }
}

@Preview
@Composable
fun VerticalSectionPreview() {

}