package com.app.kesehatan

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.app.kesehatan.databinding.ActivitySplashScreenBinding


class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }, 1000)
    }
}