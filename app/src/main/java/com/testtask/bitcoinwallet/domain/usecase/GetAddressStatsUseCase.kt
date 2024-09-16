package com.testtask.bitcoinwallet.domain.usecase

import com.testtask.bitcoinwallet.data.remote.BitcoinApiService
import com.testtask.bitcoinwallet.domain.model.BlockstreamDataClass

class GetAddressStatsUseCase(private val bitcoinApiService: BitcoinApiService) {
    suspend fun execute(address: String): BlockstreamDataClass {
        return bitcoinApiService.getAddressStats(address)
    }
}