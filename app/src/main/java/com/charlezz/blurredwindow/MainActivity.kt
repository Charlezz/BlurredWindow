package com.charlezz.blurredwindow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    lateinit var slider1: Slider
    lateinit var slider2: Slider

    lateinit var text1: TextView
    lateinit var text2: TextView

    private var blurBehindRadius = 50
    private var backgroundBlurRadius = 50

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button1).setOnClickListener {
            BlurredDialog(this, blurBehindRadius, backgroundBlurRadius).show()
        }

        findViewById<Button>(R.id.button2).setOnClickListener {
            startActivity(
                Intent(this, BlurredActivity::class.java).apply {
                    putExtra("blurBehindRadius", blurBehindRadius)
                    putExtra("backgroundBlurRadius", backgroundBlurRadius)
                }
            )
        }

        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)

        slider1 = findViewById(R.id.slider1)
        slider2 = findViewById(R.id.slider2)
        slider1.valueFrom = 0f
        slider1.valueTo = 100f
        slider2.valueFrom = 0f
        slider2.valueTo = 100f
        slider1.addOnChangeListener { slider, value, fromUser ->
            blurBehindRadius = value.toInt()
            text1.text = "blurBehindRadius = $blurBehindRadius"
        }
        slider2.addOnChangeListener { slider, value, fromUser ->
            backgroundBlurRadius = value.toInt()
            text2.text = "backgroundBlurRadius = $backgroundBlurRadius"
        }
        slider1.value = blurBehindRadius.toFloat()
        slider2.value = backgroundBlurRadius.toFloat()
    }
}