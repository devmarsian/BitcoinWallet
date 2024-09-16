package com.testtask.bitcoinwallet.di

import com.testtask.bitcoinwallet.data.BitcoinManager
import com.testtask.bitcoinwallet.data.db.AppDatabase
import com.testtask.bitcoinwallet.data.remote.BitcoinApiService
import com.testtask.bitcoinwallet.domain.repository.AddressRepository
import com.testtask.bitcoinwallet.data.repo.AddressRepositoryImpl
import com.testtask.bitcoinwallet.domain.repository.KeyRepository
import com.testtask.bitcoinwallet.data.repo.KeyRepositoryImpl
import com.testtask.bitcoinwallet.domain.usecase.AddressUseCase
import com.testtask.bitcoinwallet.domain.usecase.GetAddressStatsUseCase
import com.testtask.bitcoinwallet.domain.usecase.GetUTXOUseCase
import com.testtask.bitcoinwallet.domain.usecase.KeyUseCase
import com.testtask.bitcoinwallet.ui.viewmodel.BitcoinViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single {
        AppDatabase.getDatabase(androidContext()).addressDao()
    }
    single { BitcoinManager() }
    single<KeyRepository> { KeyRepositoryImpl(get()) }
    single<AddressRepository> { AddressRepositoryImpl(get()) }
    single { AddressUseCase(get()) }
    single { GetAddressStatsUseCase(get()) }
    single { GetUTXOUseCase(get()) }
    single { KeyUseCase(get()) }
    viewModel { BitcoinViewModel( get(), get(), get(), get()) }
}


val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://blockstream.info/signet/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(BitcoinApiService::class.java)
    }
}