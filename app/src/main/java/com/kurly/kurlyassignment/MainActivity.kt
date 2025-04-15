package com.kurly.kurlyassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.kurly.kurlyassignment.domain.model.Section
import com.kurly.kurlyassignment.presentation.theme.KurlyAssignmentTheme
import com.kurly.kurlyassignment.presentation.viewmodel.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewmodel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            KurlyAssignmentTheme(darkTheme = false) {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android"
//                    )
                    getSections()
//                }
            }
        }

    }
}

@Composable
fun getSections(viewmodel: MainViewmodel = hiltViewModel()) {
    val lazyPagingItems = viewmodel.sections.collectAsLazyPagingItems()

    LazyColumn {
        items(
            lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.id }
        ) { index ->
            lazyPagingItems[index]?.let { section: Section ->
                Text(section.title)
            } ?: let {
                // PlaceHolder..
                
            }
        }
    }
}

@Composable
fun Greeting(
    name: String
) {
    Text(
        text = "Hello $name!"
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    KurlyAssignmentTheme {
        Greeting("Android")
    }
}