package com.testtask.bitcoinwallet.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.bitcoinj.core.ECKey
import java.security.PrivateKey

@Entity(tableName = "addresses")
data class BitcoinAddress(
    @PrimaryKey (autoGenerate = true) val id: Int,
    val address: String
)
