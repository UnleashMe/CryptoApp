package com.example.cryptoapp.domain.model

data class Asset(
    val name: String = "",
    val symbol: String = "",
    val price: String = "",
    val priceChangePercentage: String = "",
    val vwap: String = "",
    val volumeUsd: String = "",
    val marketCapUsd: String = "",
    val supply: String = "",
    val maxSupply: String = "",
    val id: String = ""
)
