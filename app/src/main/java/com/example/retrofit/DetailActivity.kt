package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.retrofit.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    // Binding xml activity_detail.xml
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Menangani pengiriman data dari mainActivity
        supportActionBar!!.title = intent.getStringExtra("intent_title")

        // Menggunakan Glide untuk menambahkan animasi loading pada gambar
        Glide.with(this@DetailActivity)
            // akan membuat animasi pada setiap pemanggilan gambar dengan api
            .load(intent.getStringExtra("intent_image"))
            // Memasukan value yang di simpan pada Glide kedalam xml
            .into(binding.imageView)
    }
}