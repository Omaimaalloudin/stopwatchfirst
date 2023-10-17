package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.View
import pl.droidsonroids.gif.GifDrawable
import pl.droidsonroids.gif.GifImageView

class splashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        supportActionBar?.hide()
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        val SPLASH_DISPLAY_LENGTH = 5000 // 5 seconds

            val gifImageView = findViewById<GifImageView>(R.id.gifImageView)

            // Load the GIF from the resource
            val gifDrawable = GifDrawable.createFromResource(resources, R.drawable.splashscreen1)
            gifImageView.setImageDrawable(gifDrawable)

            // Start the animation
            gifDrawable?.start()
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, SPLASH_DISPLAY_LENGTH.toLong())
        }



    }
