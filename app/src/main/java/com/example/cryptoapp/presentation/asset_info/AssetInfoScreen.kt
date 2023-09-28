package com.example.cryptoapp.presentation.asset_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.cryptoapp.R
import com.example.cryptoapp.ui.theme.AppTheme
import com.example.cryptoapp.ui.theme.Blue
import org.koin.androidx.compose.getViewModel

@Composable
fun AssetInfoScreen(
    viewModel: AssetInfoViewModel = getViewModel(),
    paddingValues: PaddingValues,
    id: String
) {

    LaunchedEffect(key1 = Unit) {
        viewModel.onEvent(AssetInfoEvent.PassId(id))
    }

    val state = viewModel.state

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Blue)
        }
    } else if (state.error != null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = state.error,
                style = MaterialTheme.typography.displayLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    } else

        LazyColumn(verticalArrangement = Arrangement.spacedBy(AppTheme.dimens.smallMedium)) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .padding(horizontal = AppTheme.dimens.medium)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    AssetInfo(asset = state.asset)
                    Spacer(modifier = Modifier.height(AppTheme.dimens.medium))
                    Text(
                        text = stringResource(R.string.markets),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(modifier = Modifier.height(AppTheme.dimens.medium))
                }
            }
            items(state.markets) {
                MarketItem(market = it)
            }
        }

}