package com.kurly.kurlyassignment.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kurly.kurlyassignment.domain.model.SectionType
import com.kurly.kurlyassignment.domain.model.SectionWithProducts

@Composable
fun SectionContent(sectionWithProduct: SectionWithProducts) {
    Column {
        Text(
            text = sectionWithProduct.section.title,
            fontSize = 20.sp,
            style = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        when (sectionWithProduct.section.type) {
            SectionType.VERTICAL -> {
                VerticalSection(sectionWithProduct.products)
            }

            SectionType.HORIZONTAL -> {
                HorizontalSection(sectionWithProduct.products)
            }

            SectionType.GRID -> {
                GridSection(sectionWithProduct.products)
            }
        }
    }
}

@Composable
fun SectionPlaceHolder(brush: Brush) {
    Column {
        Box(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .height(24.dp) // 실제 텍스트 높이에 맞게
                .fillMaxWidth(0.3f) // 텍스트 길이 만큼 조정
                .background(brush = brush)
                .graphicsLayer { alpha = 0.5f }
        )

    }
}