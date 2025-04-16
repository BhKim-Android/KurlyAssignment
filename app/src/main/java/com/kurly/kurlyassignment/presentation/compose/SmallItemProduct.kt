package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kurly.kurlyassignment.domain.model.Product

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SmallItemProduct(
    product: Product,
    onHeightMeasured: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .onGloballyPositioned { coordinates ->
                onHeightMeasured(coordinates.size.height)
            }
    ) {
        Spacer(Modifier.height(20.dp))
        AsyncImage(
            model = product.image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(size = 5.dp))
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.name,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black
            )
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (product.discount.isNotEmpty())
                Text(
                    text = product.discount,
                    color = Color(0xFFFA6221), // #fa6221
                    style = TextStyle(fontSize = 14.sp)
                )

            Text(
                text = product.discountedPrice ?: product.originalPrice,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black
            )
        }
        product.discountedPrice?.let {
            Text(
                text = product.originalPrice,
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.LightGray,
                    textDecoration = TextDecoration.LineThrough
                )
            )
        }

        Spacer(Modifier.height(20.dp))
    }
}

@Preview
@Composable
fun SmallItemProductPreview() {

}