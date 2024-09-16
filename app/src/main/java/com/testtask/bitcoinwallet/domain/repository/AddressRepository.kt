package com.testtask.bitcoinwallet.domain.repository

import com.testtask.bitcoinwallet.data.model.BitcoinAddress

interface AddressRepository {
    suspend fun getAddress(): BitcoinAddress?
    suspend fun saveAddress(address: BitcoinAddress)
}