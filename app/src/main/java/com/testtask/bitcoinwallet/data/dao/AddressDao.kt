package com.testtask.bitcoinwallet.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.testtask.bitcoinwallet.data.model.BitcoinAddress

@Dao
interface AddressDao {

    @Query("SELECT * FROM addresses")
    suspend fun getAddress(): BitcoinAddress?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAddress(address: BitcoinAddress)
}
