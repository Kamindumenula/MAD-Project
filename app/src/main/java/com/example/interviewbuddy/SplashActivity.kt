package com.example.interviewbuddy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.airbnb.lottie.LottieAnimationView
import com.example.interviewbuddy.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Start the Lottie animation
        val loadingAnimation = findViewById<LottieAnimationView>(R.id.loadingAnimation)
        loadingAnimation.playAnimation()

        // Delay for 3 seconds then navigate to Onboarding
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnboardingActivity1::class.java)
            startActivity(intent)
            finish() // Close splash activity so user can't go back
        }, 3000) // 3000 milliseconds = 3 seconds
    }
}