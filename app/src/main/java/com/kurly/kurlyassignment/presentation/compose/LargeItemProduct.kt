package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.kurly.kurlyassignment.R
import com.kurly.kurlyassignment.domain.model.Product
import com.kurly.kurlyassignment.presentation.viewmodel.MainViewmodel

@Composable
fun LargeItemProduct(
    product: Product,
    viewModel: MainViewmodel = hiltViewModel()
) {
    val favoriteIds by viewModel.favoriteProductIds.collectAsState()
    val isFavorite = favoriteIds.contains(product.id)
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
            IconButton(
                onClick = {
                    viewModel.toggleFavoriteStatus(product.id)
                },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(
                        id = if (isFavorite) R.drawable.ic_btn_heart_on else R.drawable.ic_btn_heart_off
                    ),
                    contentDescription = if (isFavorite) "찜함" else "찜하지 않음",
                    tint = Color.Unspecified
                )
            }
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

@Composable
fun LargeItemProductPlaceHolder(brush: Brush) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f)
                .clip(RoundedCornerShape(5.dp))
                .background(brush)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(20.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(brush)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(20.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
        }
    }
}