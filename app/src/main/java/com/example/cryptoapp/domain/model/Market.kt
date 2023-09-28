package com.example.cryptoapp.domain.model

data class Market(
    val name: String,
    val price: String,
    val priceUsd: String,
    val tradesCount: Int,
    val percentageExchangeVolume: String,
    val baseSymbol: String,
    val quoteSymbol: String,
    val volumeUsd: String
)
