package com.example.cryptoapp.presentation.assets_list

import com.example.cryptoapp.domain.model.Asset

data class AssetsListState(
    val list: List<Asset> = listOf(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val page: Int = 0,
    val isEndReached: Boolean = false
)