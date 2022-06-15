package com.example.testappwithnavigation.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testappwithnavigation.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.login.setOnClickListener {
            val resultIntent = Intent().apply {
                putExtra("username", binding.username.text.toString())
                putExtra("password", binding.password.text.toString())
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()

    }

    companion object {
        fun callingIntent(
            context: Context
        ) = Intent(context, LoginActivity::class.java)
    }
}