package com.example.notesappmvvmkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.notesappmvvmkotlin.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    //View binding
    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Handle splash
        supportActionBar?.hide()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this@SplashScreen, MainActivity::class.java))
        }, 2000)
    }
}