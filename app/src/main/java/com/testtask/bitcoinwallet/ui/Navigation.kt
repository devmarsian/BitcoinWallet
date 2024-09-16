package com.testtask.bitcoinwallet.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.testtask.bitcoinwallet.ui.screens.SendBitcoinScreen
import com.testtask.bitcoinwallet.ui.screens.TransactionSuccessScreen

@Composable
fun BitcoinAppNavHost(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "send_bitcoin_screen",
        modifier = modifier
    ) {
        composable("send_bitcoin_screen") {
            SendBitcoinScreen(
                navController = navController
            )
        }

        composable("transaction_success_screen/{transactionId}") { backStackEntry ->
            val transactionId = backStackEntry.arguments?.getString("transactionId") ?: ""
            TransactionSuccessScreen(
                transactionId = transactionId,
                navController = navController
            )
        }
    }
}