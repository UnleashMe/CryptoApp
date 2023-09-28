package com.example.cryptoapp.domain.repositories

import com.example.cryptoapp.domain.model.Asset
import com.example.cryptoapp.domain.model.Market
import com.example.cryptoapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {

    suspend fun getAssets(page: Int, quan: Int): Result<List<Asset>>

    suspend fun getAssetById(id: String): Flow<Resource<Asset>>

    suspend fun getMarketById(id: String): Flow<Resource<List<Market>>>
}