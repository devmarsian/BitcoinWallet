package com.testtask.bitcoinwallet.domain.usecase

import com.testtask.bitcoinwallet.data.model.BitcoinAddress
import com.testtask.bitcoinwallet.domain.repository.AddressRepository
import com.testtask.bitcoinwallet.domain.repository.KeyRepository
import org.bitcoinj.core.ECKey

class KeyUseCase(private val keyRepo: KeyRepository) {
     fun loadKey(): ECKey? {
        return keyRepo.loadKey()
    }

     fun saveKey(key: ECKey) {
        keyRepo.saveKey(key)
    }
}