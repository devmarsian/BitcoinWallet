package com.testtask.bitcoinwallet.data.remote

import com.testtask.bitcoinwallet.domain.model.BlockstreamDataClass
import com.testtask.bitcoinwallet.domain.model.UTXO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BitcoinApiService {
    @GET("address/{address}")
    suspend fun getAddressStats(@Path("address") address: String): BlockstreamDataClass

    @GET("address/{address}/utxo")
    suspend fun getUTXO(@Path("address") address: String): List<UTXO>

    @POST("tx")
    suspend fun broadcastTransaction(@Body transaction: Map<String, String>): Response<Unit>
}
