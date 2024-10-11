package com.dicoding.dicodingeventsubmission.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.dicodingeventsubmission.HomeNavigation
import com.dicoding.dicodingeventsubmission.R
import com.dicoding.dicodingeventsubmission.ui.dashboard.DashboardFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DashboardFragment())
                .commit()
        }
    }
}