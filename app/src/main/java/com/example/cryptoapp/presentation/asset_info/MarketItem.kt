package com.example.cryptoapp.presentation.asset_info

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.model.Market
import com.example.cryptoapp.ui.theme.AppTheme
import com.example.cryptoapp.ui.theme.Blue
import com.example.cryptoapp.ui.theme.Green
import com.example.cryptoapp.ui.theme.Red
import com.example.cryptoapp.ui.theme.TextColorDark
import com.example.cryptoapp.util.safelyToDouble

@Composable
fun MarketItem(market: Market) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(AppTheme.dimens.lessThanMedium))
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = AppTheme.dimens.lessThanMedium)
            .padding(bottom = AppTheme.dimens.lessThanMedium)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .clip(
                    RoundedCornerShape(
                        bottomEnd = AppTheme.dimens.smallMedium,
                        bottomStart = AppTheme.dimens.smallMedium
                    )
                )
                .background(Blue)
                .padding(AppTheme.dimens.smallMedium)
        ) {
            Text(
                text = stringResource(R.string.market_abbr, market.baseSymbol, market.quoteSymbol),
                style = MaterialTheme.typography.labelLarge,
                color = TextColorDark
            )
        }
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = AppTheme.dimens.lessThanMedium),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = market.name, style = MaterialTheme.typography.bodyMedium)
                Text(text = market.price, style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.lessThanMedium))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.volume_usd_24h),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Text(
                    text = market.volumeUsd,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
            Spacer(modifier = Modifier.height(AppTheme.dimens.lessThanMedium))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.trades_count_24h),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = market.percentageExchangeVolume,
                        style = MaterialTheme.typography.labelLarge,
                        color = if ((market.percentageExchangeVolume.safelyToDouble()
                                ?: 0.0) < 0
                        ) Red
                        else if (market.percentageExchangeVolume.safelyToDouble() == 0.0) MaterialTheme.colorScheme.onSecondary
                        else Green
                    )
                    Spacer(modifier = Modifier.width(AppTheme.dimens.smallMedium))
                    Text(
                        text = market.tradesCount.toString(),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
        }
    }
}