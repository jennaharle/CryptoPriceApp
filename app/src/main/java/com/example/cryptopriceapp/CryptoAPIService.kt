package com.example.cryptopriceapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface CryptoApi {
    @GET("simple/price?ids=bitcoin,ethereum&vs_currencies=usd")
    fun getCryptoPrices(): Call<CryptoPriceResponse>
}


