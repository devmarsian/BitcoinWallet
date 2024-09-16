package com.testtask.bitcoinwallet.domain.model


data class BlockstreamDataClass(
    val address: String,
    val chain_stats: Stats,
    val mempool_stats: Stats
)

data class Stats(
    val funded_txo_count: Int,
    val funded_txo_sum: Long,
    val spent_txo_count: Int,
    val spent_txo_sum: Long,
    val tx_count: Int
)