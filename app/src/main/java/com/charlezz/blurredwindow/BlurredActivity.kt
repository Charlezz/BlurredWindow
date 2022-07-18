package com.charlezz.blurredwindow

import android.os.Bundle
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class BlurredActivity : AppCompatActivity() {
    lateinit var textView: TextView


    var blurBehindRadius: Int = 0
    var backgroundBlurRadius: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blurred)

        blurBehindRadius = intent.getIntExtra("blurBehindRadius", blurBehindRadius)
        backgroundBlurRadius = intent.getIntExtra("backgroundBlurRadius", backgroundBlurRadius)

        findViewById<ImageView>(R.id.close).setOnClickListener { finish() }
        textView = findViewById(R.id.text)
        textView.text = """
Cross-window Blur = ${windowManager.isCrossWindowBlurEnabled}
blurBehindRadius = $blurBehindRadius
backgroundBlurRadius = $backgroundBlurRadius
        """.trimMargin()

        setBlurredBackground()
    }

    private fun setBlurredBackground() {
        if (windowManager.isCrossWindowBlurEnabled) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
                WindowManager.LayoutParams.FLAG_BLUR_BEHIND
            )
            window.attributes.blurBehindRadius = blurBehindRadius
            window.setBackgroundBlurRadius(backgroundBlurRadius)
        }
    }
}