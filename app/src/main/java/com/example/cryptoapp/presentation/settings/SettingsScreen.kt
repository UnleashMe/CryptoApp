package com.example.cryptoapp.presentation.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.cryptoapp.R
import com.example.cryptoapp.ui.theme.AppTheme
import com.example.cryptoapp.ui.theme.Blue
import com.example.cryptoapp.ui.theme.CheckedTrackColor
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    viewModel: SettingsViewModel = getViewModel()
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = AppTheme.dimens.lessThanMedium)
            .padding(paddingValues)
            .clip(RoundedCornerShape(AppTheme.dimens.lessThanMedium)),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = AppTheme.dimens.lessThanMedium,
                    vertical = AppTheme.dimens.moreThanSmall
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.dark_theme),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Switch(
                checked = viewModel.darkThemeEnabled,
                onCheckedChange = {
                    viewModel.onEvent(SettingsEvent.OnSwitchClick(it))
                },
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = MaterialTheme.colorScheme.onSecondary,
                    checkedThumbColor = Blue,
                    uncheckedTrackColor = MaterialTheme.colorScheme.background,
                    checkedTrackColor = CheckedTrackColor,
                    uncheckedBorderColor = Color.Transparent
                )
            )
        }
    }
}