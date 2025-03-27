package com.example.interviewbuddy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import com.example.interviewbuddy.databinding.ActivityOnboarding1Binding
import com.example.interviewbuddy.R

class OnboardingActivity1 : AppCompatActivity() {
    private lateinit var binding: ActivityOnboarding1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboarding1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSkip.setOnClickListener {
            navigateToLogin()
        }

        binding.btnNext.setOnClickListener {
            navigateToScreen2()
        }
    }

    private fun navigateToScreen2() {
        val intent = Intent(this, OnboardingActivity2::class.java)
        val options = ActivityOptionsCompat.makeCustomAnimation(
            this,
            R.anim.slide_in_right,  // Enter animation
            R.anim.slide_out_left   // Exit animation
        )
        startActivity(intent, options.toBundle())
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}