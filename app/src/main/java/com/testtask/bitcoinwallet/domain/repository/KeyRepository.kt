package com.testtask.bitcoinwallet.domain.repository

import org.bitcoinj.core.ECKey

interface KeyRepository {
    fun saveKey(key: ECKey)
    fun loadKey(): ECKey?
}