package com.example.cryptoapp.presentation.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cryptoapp.R
import com.example.cryptoapp.ui.theme.AppTheme

@Composable
fun Appbar(
    isPrimary: Boolean,
    title: String = "",
    text: String = "",
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(
                horizontal = if (isPrimary) AppTheme.dimens.medium else 0.dp,
                vertical = AppTheme.dimens.smallMedium
            ),
        horizontalArrangement = if (isPrimary) Arrangement.SpaceBetween else Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (!isPrimary) {
            Box(
                modifier = Modifier
                    .padding(AppTheme.dimens.evenLessThanMedium)
                    .size(AppTheme.dimens.sizeable)
                    .clip(RoundedCornerShape(AppTheme.dimens.lessThanMedium))
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable {
                        navController.popBackStack()
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(modifier = Modifier.width(AppTheme.dimens.medium))
        }
        Row(verticalAlignment = Alignment.Bottom) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(end = AppTheme.dimens.smallMedium)
            )
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondary
            )
        }
        if (isPrimary) {
            Box(
                modifier = Modifier
                    .padding(AppTheme.dimens.evenLessThanMedium)
                    .size(AppTheme.dimens.sizeable)
                    .clip(RoundedCornerShape(AppTheme.dimens.lessThanMedium))
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable { navController.navigate(Screen.Settings.route) }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_more_horiz_24),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}