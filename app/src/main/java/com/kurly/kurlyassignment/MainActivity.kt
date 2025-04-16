package com.kurly.kurlyassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.kurly.kurlyassignment.domain.model.SectionType
import com.kurly.kurlyassignment.presentation.compose.GridSection
import com.kurly.kurlyassignment.presentation.compose.HorizontalSection
import com.kurly.kurlyassignment.presentation.compose.VerticalSection
import com.kurly.kurlyassignment.presentation.theme.KurlyAssignmentTheme
import com.kurly.kurlyassignment.presentation.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KurlyAssignmentTheme(darkTheme = false) {
                getSections()
            }
        }

    }
}

@Composable
fun getSections(viewmodel: MainViewmodel = hiltViewModel()) {
    val lazyPagingItems = viewmodel.sectionsWithProductsFlow.collectAsLazyPagingItems()

    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        items(
            lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.section.id }
        ) { index ->
            lazyPagingItems[index]?.let { sectionWithProduct ->
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

            if (index < lazyPagingItems.itemCount - 1) {
                HorizontalDivider(
                    thickness = 1.dp,
                    color = Color.Gray
                )
            }
        }
    }
}