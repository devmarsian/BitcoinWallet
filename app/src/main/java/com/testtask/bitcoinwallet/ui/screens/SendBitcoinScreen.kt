package com.testtask.bitcoinwallet.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.testtask.bitcoinwallet.ui.viewmodel.BitcoinViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SendBitcoinScreen(
    navController: NavHostController,
) {
    val viewModel = koinViewModel<BitcoinViewModel>()
    var amount by remember { mutableStateOf("") }
    var addr by remember { mutableStateOf("") }
    var transactionId by remember { mutableStateOf<String?>(null) }

    val address = viewModel.address
    val utxoData = viewModel.utxoData
    val balance = viewModel.balanceBTC

    val coroutineScope = rememberCoroutineScope()

    viewModel.fetchAddressStats()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bitcoin Wallet", style = MaterialTheme.typography.headlineLarge)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Address: ${address ?: "Loading..."}", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "$balance BTC",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))


        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = addr,
            onValueChange = { addr = it },
            label = { Text("Recipient Address") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount to Send (BTC)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    viewModel.fetchUTXOs()
                    if (viewModel.utxoData.isNotEmpty()) {
//                        viewModel.createAndSendTransaction(addr, amount, privateKey)
                    } else {
                        Log.d("transaction", "No UTXOs available to create transaction.")
                    }
                }
            }
        ) {
            Text("Send")
        }
    }
}