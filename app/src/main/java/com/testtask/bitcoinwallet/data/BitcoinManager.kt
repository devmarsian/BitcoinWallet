package com.testtask.bitcoinwallet.data

import com.testtask.bitcoinwallet.network.SignetParams
import com.testtask.bitcoinwallet.domain.model.UTXO
import org.bitcoinj.core.Address
import org.bitcoinj.core.Coin
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.LegacyAddress
import org.bitcoinj.core.Sha256Hash
import org.bitcoinj.core.Transaction
import org.bitcoinj.core.TransactionOutPoint
import org.bitcoinj.script.Script
import java.net.HttpURLConnection
import java.net.URL


class BitcoinManager {
    val params = SignetParams.SIGNET_PARAMS

    @OptIn(ExperimentalStdlibApi::class)
    fun sendTransaction(transaction: Transaction) {
        val url = URL("https://blockstream.info/signet/api/tx")
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "POST"
        connection.doOutput = true

        val transactionHex = transaction.bitcoinSerialize().toHexString()
        val outputStream = connection.outputStream
        outputStream.write(transactionHex.toByteArray())
        outputStream.flush()
        outputStream.close()

        val responseCode = connection.responseCode
    }
}
