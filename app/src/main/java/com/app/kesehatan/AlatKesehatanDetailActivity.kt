package com.app.kesehatan

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.kesehatan.databinding.ActivityAlatKesehatanDetailBinding
import com.app.kesehatan.databinding.ActivitySplashScreenBinding
import com.bumptech.glide.Glide

class AlatKesehatanDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlatKesehatanDetailBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlatKesehatanDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initToolbar()
        if(intent.extras != null)
        {

            val bundle = intent.extras
            binding.tvNama.text = bundle!!.getString("nama_alat")
            binding.tvDeskripsi.text = bundle.getString("deskripsi")

            Glide.with(this)
                .load(Uri.parse("file:///android_asset/gambar/"+bundle.getString("img_detail")))
                .into(binding.imgDetail)
        }


    }

    private fun initToolbar() {
        val actionBar = supportActionBar
        actionBar!!.title = "Detail"
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}