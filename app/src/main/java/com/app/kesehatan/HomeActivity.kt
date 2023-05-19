package com.app.kesehatan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.kesehatan.databinding.ActivityHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        initToolbar()

        binding.cardAlatKesehatan.setOnClickListener {
            val intent = Intent(this, AlatKesehatanListActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.cardTentang.setOnClickListener {
            val intent = Intent(this, TentangAplikasiActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }

        binding.cardKeluarApp.setOnClickListener {
            MaterialAlertDialogBuilder(this).setTitle("Keluar Dari Aplikasi ?")
                .setPositiveButton("Ya, Keluar") { dialog, _ ->
                    finishAffinity()
                }.setNegativeButton("Batal") { dialog, _ ->

                }.show()
        }

    }

    private fun initToolbar() {
        val actionBar = supportActionBar
        actionBar!!.title = "Aplikasi Alat Kesehatan"
    }

}