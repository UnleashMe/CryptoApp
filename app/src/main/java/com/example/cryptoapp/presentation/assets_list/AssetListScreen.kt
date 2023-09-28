package com.example.cryptoapp.presentation.assets_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cryptoapp.presentation.navigation.Screen
import org.koin.androidx.compose.getViewModel

@Composable
fun AssetListScreen(
    viewModel: AssetsListViewModel = getViewModel(),
    paddingValues: PaddingValues,
    navController: NavController
) {
    val state = viewModel.state

    if (state.list.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            items(state.list.size) { i ->
                AssetItem(asset = state.list[i]) { id, name, abbr ->
                    viewModel.onEvent(AssetListEvent.SetTitle(name, abbr))
                    navController.navigate(Screen.AssetInfo.passId(id))
                }
                if (i >= state.list.size - 1 && !state.isEndReached && !state.isLoading) {
                    viewModel.onEvent(AssetListEvent.LoadNextItems)
                }
            }
            item {
                if (state.isLoading) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp), horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

    if (state.error != null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = state.error,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}