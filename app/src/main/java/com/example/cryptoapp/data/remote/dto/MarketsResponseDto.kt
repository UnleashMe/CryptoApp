package com.example.cryptoapp.data.remote.dto

data class MarketsResponseDto(
    val `data`: List<MarketData>,
    val timestamp: Long
)

