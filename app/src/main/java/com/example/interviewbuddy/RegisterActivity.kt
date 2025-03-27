package com.example.interviewbuddy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.interviewbuddy.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Register button click - immediately go to Login
        binding.btnRegister.setOnClickListener {
            navigateToLogin()
        }

        // Login text click - go back to Login
        binding.tvLogin.setOnClickListener {
            navigateToLogin()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()  // Remove RegisterActivity from back stack
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
    }
}