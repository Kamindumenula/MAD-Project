package com.example.interviewbuddy

import android.app.Activity
import android.content.Intent
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView

class NavigationBar(
    private val currentActivity: Activity,
    private val bottomNav: BottomNavigationView
) : BottomNavigationView.OnNavigationItemSelectedListener {

    init {
        bottomNav.selectedItemId = getCurrentNavItemId() // Highlight current tab
        bottomNav.setOnNavigationItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.navigation_home -> {
                if (currentActivity !is MainActivity) {
                    startActivity(MainActivity::class.java)
                }
                return true
            }
            R.id.navigation_interview -> {
                if (currentActivity !is TestInterview) {
                    startActivity(TestInterview::class.java)
                }
                return true
            }
            // Add other cases for History/Settings
        }
        return false
    }

    private fun startActivity(activityClass: Class<*>) {
        val intent = Intent(currentActivity, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_NO_ANIMATION // Optional: Disable transition
        currentActivity.startActivity(intent)
        currentActivity.overridePendingTransition(0, 0) // Remove animation
    }

    private fun getCurrentNavItemId(): Int {
        return when (currentActivity) {
            is MainActivity -> R.id.navigation_home
            is TestInterview -> R.id.navigation_interview
            // Add other activities
            else -> R.id.navigation_home
        }
    }

    companion object {
        fun setup(activity: Activity) {
            val bottomNav = activity.findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            NavigationBar(activity, bottomNav)
        }
    }
}