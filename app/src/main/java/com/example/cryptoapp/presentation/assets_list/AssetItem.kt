package com.example.cryptoapp.presentation.assets_list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.example.cryptoapp.domain.model.Asset
import com.example.cryptoapp.ui.theme.AppTheme
import com.example.cryptoapp.ui.theme.Green
import com.example.cryptoapp.ui.theme.Red

@Composable
fun AssetItem(
    asset: Asset,
    onAssetClick: (String, String, String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.lessThanMedium))
            .padding(horizontal = AppTheme.dimens.medium)
            .padding(bottom = AppTheme.dimens.smallMedium)
            .clickable {
                onAssetClick(asset.id, asset.name, asset.symbol)
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(AppTheme.dimens.lessThanMedium),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = asset.name,
                    modifier = Modifier.padding(bottom = AppTheme.dimens.small),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = asset.symbol,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.End) {
                Text(
                    text = asset.price,
                    modifier = Modifier.padding(bottom = AppTheme.dimens.small),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = asset.priceChangePercentage,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (asset.priceChangePercentage.startsWith('-')) Red else Green
                )
            }
        }
    }
}