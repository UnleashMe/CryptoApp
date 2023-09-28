package com.example.cryptoapp.data.mapper

import com.example.cryptoapp.data.remote.dto.AssetData
import com.example.cryptoapp.data.remote.dto.MarketData
import com.example.cryptoapp.domain.model.Asset
import com.example.cryptoapp.domain.model.Market

fun AssetData.toAsset(): Asset {
    val pcpSign = if (changePercent24Hr.startsWith('-')) "" else "+"
    return Asset(
        name = name,
        symbol = symbol,
        price = "$ " + priceUsd.take(priceUsd.takeWhile { it != '.' }.length + 3),
        priceChangePercentage = pcpSign + changePercent24Hr.take(changePercent24Hr.takeWhile { it != '.' }.length + 3) + "%",
        vwap = vwap24Hr?.let { s ->
            "$ " + s.take(vwap24Hr.takeWhile { it != '.' }.length + 3)
        } ?: "-",
        volumeUsd = "$ " + volumeUsd24Hr.take(volumeUsd24Hr.takeWhile { it != '.' }.length + 3),
        marketCapUsd = "$ " + marketCapUsd.take(marketCapUsd.takeWhile { it != '.' }.length + 3),
        supply = supply.take(supply.takeWhile { it != '.' }.length + 3) + " " + symbol,
        maxSupply = maxSupply?.let { s ->
            s.take(s.takeWhile { it != '.' }.length + 3) + " " + symbol
        } ?: "-",
        id = id
    )
}

fun MarketData.toMarket(): Market {
    val pevSign = if (percentExchangeVolume?.startsWith('-') == true) "" else "+ "
    return Market(
        name = this.exchangeId[0].uppercase() + this.exchangeId.drop(1),
        price = priceQuote?.let {
            "$ " + it.take(priceQuote.takeWhile { it != '.' }.length + 3)
        } ?: "-",
        priceUsd = priceUsd?.take(priceUsd.takeWhile { it != '.' }.length + 3) ?: "-",
        tradesCount = tradesCount24Hr?.toInt() ?: 0,
        percentageExchangeVolume = percentExchangeVolume?.let { s ->
            pevSign + s.take(s.takeWhile { it != '.' }.length + 3) + "%"
        } ?: "",
        baseSymbol = baseSymbol,
        quoteSymbol = quoteSymbol,
        volumeUsd = volumeUsd24Hr?.take(volumeUsd24Hr.takeWhile { it != '.' }.length + 3) ?: "-",
    )
}