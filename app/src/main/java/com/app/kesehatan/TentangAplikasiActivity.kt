package com.app.kesehatan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.kesehatan.databinding.ActivityHomeBinding
import com.app.kesehatan.databinding.ActivityTentangAplikasiBinding

class TentangAplikasiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTentangAplikasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTentangAplikasiBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initToolbar()
    }

    private fun initToolbar() {
        val actionBar = supportActionBar
        actionBar!!.title = "Tentang Aplikasi"
    }
}