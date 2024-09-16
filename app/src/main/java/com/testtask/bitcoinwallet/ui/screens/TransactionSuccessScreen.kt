package com.testtask.bitcoinwallet.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun TransactionSuccessScreen(
    transactionId: String,
    navController: NavController
) {
    val context = LocalContext.current
    val intent = remember { Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Your funds have been sent!",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Your transaction ID",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = transactionId,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Blue,
            modifier = Modifier.clickable {
                context.startActivity(intent)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                navController.navigate("send_bitcoin_screen") {
                    popUpTo("send_bitcoin_screen") { inclusive = true }
                }
            }
        ) {
            Text("Send More")
        }
    }
}
