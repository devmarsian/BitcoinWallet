package com.testtask.bitcoinwallet.data.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.testtask.bitcoinwallet.data.model.BitcoinAddress
import com.testtask.bitcoinwallet.data.dao.AddressDao

@Database(entities = [BitcoinAddress::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}