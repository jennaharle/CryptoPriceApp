package com.example.cryptopriceapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var cryptoInput: EditText
    private lateinit var fetchButton: Button
    private lateinit var priceTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        cryptoInput = findViewById(R.id.cryptoInput)
        fetchButton = findViewById(R.id.fetchButton)
        priceTextView = findViewById(R.id.priceTextView)


        fetchButton.setOnClickListener {
            fetchCryptoPrice()
        }
    }


    private fun fetchCryptoPrice() {
        val cryptoName = cryptoInput.text.toString().lowercase()
        if (cryptoName.isNotEmpty()) {
            RetrofitInstance.api.getCryptoPrices().enqueue(object : Callback<CryptoPriceResponse> {
                override fun onResponse(
                    call: Call<CryptoPriceResponse>,
                    response: Response<CryptoPriceResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { cryptoPrices ->
                            when (cryptoName) {
                                "bitcoin" -> {
                                    priceTextView.text = "Hinta: ${cryptoPrices.bitcoin.usd} USD"
                                }

                                "ethereum" -> {
                                    priceTextView.text = "Hinta: ${cryptoPrices.ethereum.usd} USD"
                                }

                                else -> {
                                    priceTextView.text = "Hinta: $cryptoName ei ole saatavilla"
                                }
                            }
                        }
                    } else {
                        priceTextView.text = "Virhe haettaessa tietoja"
                    }
                }

                override fun onFailure(call: Call<CryptoPriceResponse>, t: Throwable) {
                    priceTextView.text = "Verkkovirhe: ${t.message}"
                }
            })
        } else {
            priceTextView.text = "Syötä kryptovaluutan nimi"
        }
    }
}

