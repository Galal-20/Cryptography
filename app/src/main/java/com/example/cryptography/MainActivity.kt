package com.example.cryptography

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import com.example.cryptography.databinding.ActivityMainBinding
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

@SuppressLint("GetInstance")
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var key:String = "myKeyToEn&De2080"
    var secretKeySpec = SecretKeySpec(key.toByteArray(),"AES")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btEncrypt.setOnClickListener {
            binding.txtEncrypt.setText(encrypt(binding.etCrypto1.text.toString()))
            binding.etDecrypt2.setText(binding.txtEncrypt.text.toString())
        }

        binding.btDecrypt.setOnClickListener {
            binding.txtDecrypt.setText(decrypt(binding.etDecrypt2.text.toString()))
        }
    }


    private fun encrypt(string: String) : String{
        var cipher = Cipher.getInstance("AES/ECB/PKCS5Padding") //Specifying which mode of AES is to be used
        cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec)// Specifying the mode wither encrypt or decrypt
        var encryptBytes =cipher.doFinal(string.toByteArray(Charsets.UTF_8))//Converting the string that will be encrypted to byte array
        return Base64.encodeToString(encryptBytes, Base64.DEFAULT) // returning the encrypted string
    }

    private fun decrypt(string : String) : String{
        var cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec)
        var decryptedBytes = cipher.doFinal(Base64.decode(string, Base64.DEFAULT)) // decoding the entered string
        return String(decryptedBytes,Charsets.UTF_8) // returning the decrypted string
    }
}