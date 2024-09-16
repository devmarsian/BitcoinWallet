package com.testtask.bitcoinwallet.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.testtask.bitcoinwallet.data.model.BitcoinAddress
import com.testtask.bitcoinwallet.data.BitcoinManager
import com.testtask.bitcoinwallet.network.SignetParams
import com.testtask.bitcoinwallet.domain.repository.KeyRepository
import com.testtask.bitcoinwallet.domain.model.UTXO
import com.testtask.bitcoinwallet.domain.usecase.AddressUseCase
import com.testtask.bitcoinwallet.domain.usecase.GetAddressStatsUseCase
import com.testtask.bitcoinwallet.domain.usecase.GetUTXOUseCase
import com.testtask.bitcoinwallet.domain.usecase.KeyUseCase
import kotlinx.coroutines.launch
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.LegacyAddress
import org.koin.core.component.KoinComponent
import java.util.Locale

class BitcoinViewModel(
    private val addressUseCase: AddressUseCase,
    private val getUtxoUseCase: GetUTXOUseCase,
    private val getAddressStatsUseCase: GetAddressStatsUseCase,
    private val keyUseCase: KeyUseCase
) : ViewModel(), KoinComponent {

    var address by mutableStateOf<String?>(null)
        private set

    var utxoData by mutableStateOf<List<UTXO>>(emptyList())
        private set

    var balanceBTC by mutableStateOf("0")
        private set

    var transactionIds by mutableStateOf<String?>(null)
        private set

    init {
        fetchOrGenerateAddress()
    }

    fun fetchUTXOs() {
        viewModelScope.launch {
            try {
                val utxoList = address?.let { getUtxoUseCase.execute(it) }
                if (utxoList != null) {
                    utxoData = utxoList
                }
                Log.d("RESPONSEVN", utxoData.toString())
            } catch (e: Exception) {
                Log.d("RESPONSEVN", "Failed to fetch UTXOs: ${e.message}")
            }
        }
    }

    fun fetchAddressStats() {
        viewModelScope.launch {
            address?.let { addr ->
                try {
                    val response = getAddressStatsUseCase.execute(address!!)
                    val fund = response.chain_stats.funded_txo_sum
                    val spent = response.chain_stats.spent_txo_sum
                    balanceBTC = (
                        String.format(Locale.US, "%.7f", ((fund - spent) / 100_000_000.0))
                    )
                } catch (e: Exception) {
                    Log.e("BitcoinViewModel", "Failed to fetch address stats: ${e.message}")
                }
            }
        }
    }


    private fun fetchOrGenerateAddress() {
        viewModelScope.launch {
            val savedAddress = addressUseCase.getAddress()?.address
            if (savedAddress == null) {
                val key = generateBitcoinKey()
                val newAddress = generateBitcoinAddress(key)
                keyUseCase.saveKey(key)
                saveAddress(newAddress)
                address = newAddress
            } else {
                address = savedAddress
            }
        }
    }

    private fun generateBitcoinKey(): ECKey {
        return ECKey()
    }

    private fun saveAddress(address: String) {
        viewModelScope.launch {
            addressUseCase.saveAddress(BitcoinAddress(id = 1, address = address ))
        }
    }

    private fun generateBitcoinAddress(key: ECKey): String {
        return LegacyAddress.fromKey(SignetParams.SIGNET_PARAMS, key).toString()
    }
}