package com.example.cryptoapp.presentation.asset_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.domain.model.Asset
import com.example.cryptoapp.domain.repositories.CryptoRepository
import com.example.cryptoapp.util.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class AssetInfoViewModel(
    private val repository: CryptoRepository
) : ViewModel() {

    var state by mutableStateOf(AssetInfoState())

    fun onEvent(event: AssetInfoEvent) {
        when (event) {
            is AssetInfoEvent.PassId -> passId(event.id)
        }
    }

    private fun passId(id: String) = viewModelScope.launch {
        val assetDef = async {
            repository.getAssetById(id)
        }
        val marketsDef = async {
            repository.getMarketById(id)
        }
        val asset = assetDef.await()
        val market = marketsDef.await()
        asset.collect {
            state = when (it) {
                is Resource.Success -> state.copy(asset = it.data ?: Asset(), isLoading = false)
                is Resource.Loading -> state.copy(isLoading = true)
                is Resource.Error -> state.copy(isLoading = false, error = it.message)
            }
        }
        market.collect {
            state = when (it) {
                is Resource.Success -> state.copy(markets = it.data ?: listOf(), isLoading = false)
                is Resource.Loading -> state.copy(isLoading = true)
                is Resource.Error -> state.copy(isLoading = false, error = it.message)
            }
        }
    }
}