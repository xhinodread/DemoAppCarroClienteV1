package com.example.democarrodespacho.ui.view

import android.content.Intent
import com.example.democarrodespacho.databinding.ActivityLoginFprintBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.biometric.BiometricPrompt
import com.example.democarrodespacho.ui.view.MainActivity
import java.util.concurrent.Executor

private lateinit var binding: ActivityLoginFprintBinding
private lateinit var executor: Executor
private lateinit var biometricPrompt: BiometricPrompt
private lateinit var promptInfo: BiometricPrompt.PromptInfo

class LoginFPrintActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginFprintBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        biometricLogin()
        promptInfo = BiometricPrompt.PromptInfo.Builder()
        .setTitle("Control Biometrico Demo Despacho")
        .setSubtitle("Log in usando tu credencial biometrica")
        .setNegativeButtonText("Use su huella dactilar para ingresar")
        .build()

        binding.btnBiometricLogin.setOnClickListener {
            biometricPrompt.authenticate(promptInfo)
        }
    }
    private fun biometricLogin(){
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    //Log.d("onCreate", errorCode.toString())
                    alertaToast("Authentication passw error: $errString")
                    if(errorCode == 11){
                        startActivity(Intent(this@LoginFPrintActivity, MainActivity::class.java))
                        finish()
                    }
                }

                override fun onAuthenticationSucceeded(
                    result: androidx.biometric.BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    alertaToast("Authentication succeedida!")
                    startActivity(Intent(this@LoginFPrintActivity, MainActivity::class.java))
                    finish()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    alertaToast("Authentication fallida!")
                }
            })
    }

    private fun alertaToast(texto: String){
        Toast.makeText(applicationContext, texto,
            Toast.LENGTH_SHORT)
            .show()
    }
}