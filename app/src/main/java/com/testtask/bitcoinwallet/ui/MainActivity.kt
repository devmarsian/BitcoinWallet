package com.testtask.bitcoinwallet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.testtask.bitcoinwallet.ui.theme.BitcoinWalletTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    BitcoinWalletTheme {
        BitcoinAppNavHost()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMyApp() {
    MyApp()
}