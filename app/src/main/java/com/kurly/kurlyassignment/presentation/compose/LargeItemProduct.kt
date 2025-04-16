package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.kurly.kurlyassignment.domain.model.Product

@Composable
fun LargeItemProduct(product: Product) {
    Column {
        Box {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3f / 2f)
                    .clip(RoundedCornerShape(size = 5.dp))
            )
        }
        Text(
            text = product.name,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            if (product.discount.isNotEmpty())
                Text(
                    text = product.discount,
                    color = Color(0xFFFA622F),
                    fontSize = 16.sp
                )

            Text(
                text = product.discountedPrice ?: product.originalPrice,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            if (product.discountedPrice != null) {
                Text(
                    text = product.originalPrice,
                    style = TextStyle(
                        textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray
                    ),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Preview
@Composable
fun LargeItemProductPreview() {

}