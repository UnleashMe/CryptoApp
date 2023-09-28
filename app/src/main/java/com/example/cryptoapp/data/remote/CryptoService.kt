package com.example.cryptoapp.data.remote

import com.example.cryptoapp.data.remote.dto.AssetDto
import com.example.cryptoapp.data.remote.dto.AssetsListDto
import com.example.cryptoapp.data.remote.dto.MarketsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoService {

    @GET("assets")
    suspend fun getAssets(): AssetsListDto

    @GET("assets/{id}")
    suspend fun getAssetById(@Path("id") id: String): AssetDto

    @GET("markets?baseId=bitcoin")
    suspend fun getMarketsById(@Query("baseId") id: String): MarketsResponseDto
}