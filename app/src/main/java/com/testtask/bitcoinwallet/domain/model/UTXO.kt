package com.testtask.bitcoinwallet.domain.model

import org.bitcoinj.core.Coin

data class UTXO(
    val txid: String,
    val vout: Int,
    val value: Coin,
    val status: Status
)
data class Status(
    val confirmed: Boolean,
    val block_height: Int,
    val block_hash: String,
    val block_time: Long
)