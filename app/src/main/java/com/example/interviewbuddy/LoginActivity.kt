package com.example.interviewbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.interviewbuddy.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set all click listeners to redirect to home
        binding.btnLogin.setOnClickListener { redirectToHome() }
        binding.btnGoogleLogin.root.setOnClickListener { redirectToHome() }
        binding.btnAppleLogin.root.setOnClickListener { redirectToHome() }

        // Register text click navigates to RegisterActivity
        binding.tvRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun redirectToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()  // Remove login activity from back stack
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }
}