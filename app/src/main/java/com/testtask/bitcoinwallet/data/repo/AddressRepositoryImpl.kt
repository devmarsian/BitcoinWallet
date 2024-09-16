package com.testtask.bitcoinwallet.data.repo

import com.testtask.bitcoinwallet.data.dao.AddressDao
import com.testtask.bitcoinwallet.data.model.BitcoinAddress
import com.testtask.bitcoinwallet.domain.repository.AddressRepository

class AddressRepositoryImpl(private val addressDao: AddressDao) : AddressRepository {

    override suspend fun getAddress(): BitcoinAddress? {
        return addressDao.getAddress()
    }

    override suspend fun saveAddress(address: BitcoinAddress) {
        addressDao.saveAddress(address)
    }
}