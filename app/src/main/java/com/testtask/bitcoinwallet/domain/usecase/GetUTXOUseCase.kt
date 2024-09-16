package com.testtask.bitcoinwallet.domain.usecase

import com.testtask.bitcoinwallet.data.remote.BitcoinApiService
import com.testtask.bitcoinwallet.domain.model.UTXO

class GetUTXOUseCase(private val bitcoinApiService: BitcoinApiService)  {
    suspend fun execute(address: String): List<UTXO> {
        return bitcoinApiService.getUTXO(address)
    }
}