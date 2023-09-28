package com.example.cryptoapp.presentation.asset_info

import com.example.cryptoapp.domain.model.Asset
import com.example.cryptoapp.domain.model.Market

data class AssetInfoState(
    val asset: Asset = Asset(),
    val markets: List<Market> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)
