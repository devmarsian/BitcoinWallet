package com.testtask.bitcoinwallet.domain.usecase

import com.testtask.bitcoinwallet.data.model.BitcoinAddress
import com.testtask.bitcoinwallet.domain.repository.AddressRepository


class AddressUseCase(private val addressRepo: AddressRepository) {

    suspend fun getAddress(): BitcoinAddress? {
        return addressRepo.getAddress()
    }

    suspend fun saveAddress(address: BitcoinAddress) {
        addressRepo.saveAddress(address)
    }
}

