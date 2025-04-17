package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.layout.onGloballyPositioned
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SmallItemProduct(
    product: Product,
    onHeightMeasured: (Int) -> Unit,
    viewModel: MainViewmodel = hiltViewModel()
) {
    val favoriteIds by viewModel.favoriteProductIds.collectAsState()
    val isFavorite = favoriteIds.contains(product.id)
    Column(
        modifier = Modifier
            .width(150.dp)
            .onGloballyPositioned { coordinates ->
                onHeightMeasured(coordinates.size.height)
            }
    ) {
        Spacer(Modifier.height(20.dp))
        Box {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
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
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (product.discount.isNotEmpty())
                Text(
                    text = product.discount,
                    color = Color(0xFFFA6221), // #fa6221
                    style = TextStyle(fontSize = 14.sp),
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                )

            Text(
                text = product.discountedPrice ?: product.originalPrice,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
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

@Composable
fun SmallItemProductPlaceHolder(brush: Brush) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(400.dp)
    ) {
        Spacer(Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(size = 5.dp))
                .background(brush)
        )

        Spacer(modifier = Modifier.height(8.dp))

        repeat(2) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(14.dp)
                    .padding(vertical = 2.dp)
                    .background(brush, shape = RoundedCornerShape(4.dp))
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(40.dp)
                    .height(14.dp)
                    .background(brush, RoundedCornerShape(4.dp))
            )
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .height(16.dp)
                    .background(brush, RoundedCornerShape(4.dp))
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .width(50.dp)
                .height(14.dp)
                .background(brush, RoundedCornerShape(4.dp))
        )

        Spacer(Modifier.height(20.dp))
    }
}