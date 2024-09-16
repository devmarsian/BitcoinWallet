package com.testtask.bitcoinwallet.data.repo

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.testtask.bitcoinwallet.domain.repository.KeyRepository
import org.bitcoinj.core.ECKey
import org.bitcoinj.core.Utils



class KeyRepositoryImpl(context: Context) : KeyRepository {
    private val sharedPreferences = getEncryptedSharedPreferences(context)
    override fun saveKey(key: ECKey) {
        val editor = sharedPreferences.edit()
        editor.putString("private_key", key.privateKeyAsHex)
        editor.apply()
    }

    override fun loadKey(): ECKey? {
        val privateKeyHex = sharedPreferences.getString("private_key", null)
        val privateKeyBytes = privateKeyHex?.let { Utils.HEX.decode(it) }
        return ECKey.fromPrivate(privateKeyBytes)
    }
}

fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    return EncryptedSharedPreferences.create(
        "encrypted_prefs_bitcoin_wallet",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}