package com.kurly.kurlyassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.kurly.kurlyassignment.presentation.compose.HorizontalPlaceHolder
import com.kurly.kurlyassignment.presentation.compose.SectionContent
import com.kurly.kurlyassignment.presentation.compose.SectionPlaceHolder
import com.kurly.kurlyassignment.presentation.compose.VerticalSectionPlaceHolder
import com.kurly.kurlyassignment.presentation.compose.rememberShimmerBrush
import com.kurly.kurlyassignment.presentation.theme.KurlyAssignmentTheme
import com.kurly.kurlyassignment.presentation.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KurlyAssignmentTheme(darkTheme = false) {
                Sections()
            }
        }

    }
}

@Composable
fun Sections(viewmodel: MainViewmodel = hiltViewModel()) {
    val lazyPagingItems = viewmodel.sectionsWithProductsFlow.collectAsLazyPagingItems()
    val brush = rememberShimmerBrush()
    LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
        if (lazyPagingItems.loadState.refresh is LoadState.Loading) {
            items(5) {
                SectionPlaceHolder(brush)
                HorizontalPlaceHolder(brush)
                VerticalSectionPlaceHolder(brush)
            }
        } else {
            items(
                lazyPagingItems.itemCount,
                key = lazyPagingItems.itemKey { it.section.id }
            ) { index ->
                lazyPagingItems[index]?.let { sectionWithProduct ->
                    SectionContent(sectionWithProduct)
                }

                if (index < lazyPagingItems.itemCount - 1) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = Color.Gray
                    )
                }
            }
        }

        if (lazyPagingItems.loadState.append is LoadState.Loading) {
            item {
                SectionPlaceHolder(brush)
                HorizontalPlaceHolder(brush)
                VerticalSectionPlaceHolder(brush)
            }
        }
    }
}