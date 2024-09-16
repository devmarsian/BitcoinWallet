package com.testtask.bitcoinwallet.network

import org.bitcoinj.core.Block
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.params.AbstractBitcoinNetParams

class SignetParams : AbstractBitcoinNetParams() {
    init {
        id = "org.bitcoin.signet"
        addressHeader = 111
        p2shHeader = 196
        port = 38333
        packetMagic = 0x0B110907
        dumpedPrivateKeyHeader = 239
        segwitAddressHrp = "sb"
    }

    companion object {
        val SIGNET_PARAMS: SignetParams = SignetParams()
    }

    override fun getPaymentProtocolId(): String {
        return "org.bitcoin.signet"
    }

    override fun getGenesisBlock(): Block {
        return Block(this, 0L, Sha256Hash.ZERO_HASH, Sha256Hash.ZERO_HASH, 0L, 0L, 0L, emptyList())
    }
}

