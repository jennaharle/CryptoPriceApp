package com.example.cryptopriceapp

data class CryptoPriceResponse(
    val bitcoin: CurrencyPrice,
    val ethereum: CurrencyPrice
)

data class CurrencyPrice(
    val usd: Double
)