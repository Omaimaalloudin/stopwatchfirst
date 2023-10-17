
package com.example.stopwatch
import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.stopwatch.R
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var stopwatchDisplay: TextView
    private lateinit var imageView: ImageView
    private lateinit var imageView2: ImageView
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var animation: Animation
    private var seconds = 0
    private val handler = Handler()
    private var isRunning = false
    private var isStopped = false
    private var timeElapsed = 0L
    private var startTime = 0L


    private val stopwatchRunnable = object : Runnable {
        override fun run() {
            stopwatchDisplay()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        stopwatchDisplay = findViewById(R.id.stopwatchDisplay)
        imageView = findViewById(R.id.img)
        // imageView2 = findViewById(R.id.arrow)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)
        animation = AnimationUtils.loadAnimation(this, R.anim.round)


        startButton.setOnClickListener {
            if (!isRunning) {
                if (isStopped) {
                    startTime = SystemClock.elapsedRealtime() - timeElapsed
                } else {
                    startTime = SystemClock.elapsedRealtime()
                }
                handler.post(stopwatchRunnable)
                imageView.startAnimation(animation)
                //imageView2.startAnimation(animation)
                isRunning = true
                isStopped = false
                startButton.text = "Resume"
                stopButton.text = "Stop"
            }
        }

        stopButton.setOnClickListener {
            if (isRunning) {
                handler.removeCallbacks(stopwatchRunnable)
                imageView.clearAnimation()
                // imageView2.clearAnimation()
                isRunning = false
                isStopped = true
                startButton.text = "Resume"
                stopButton.text = "Stop"
            }
        }

        resetButton.setOnClickListener {
            handler.removeCallbacks(stopwatchRunnable)
            imageView.clearAnimation()
            isRunning = false
            isStopped = false
            timeElapsed = 0
            startTime = 0
            stopwatchDisplay.text = "00:00.00"
            startButton.text = "Start"
            stopButton.text = "Stop"
        }
    }

    private fun stopwatchDisplay() {

                seconds++
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60
                stopwatchDisplay.text = String.format("%02d:%02d:%02d", hours, minutes, secs)
        handler.postDelayed(stopwatchRunnable, 1000)
            }
        }


