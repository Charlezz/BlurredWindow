package com.charlezz.blurredwindow

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class BlurredDialog(
    context: Context,
    private val blurBehindRadius: Int = 0,
    private val backgroundBlurRadius: Int = 0
) : Dialog(context, R.style.Theme_Translucent) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_blurred)
        findViewById<ImageView>(R.id.close).setOnClickListener {
            dismiss()
        }
        findViewById<TextView>(R.id.text).text = """
Cross-window Blur = ${window?.windowManager?.isCrossWindowBlurEnabled}
blurBehindRadius = $blurBehindRadius
backgroundBlurRadius = $backgroundBlurRadius
        """.trimIndent()
        window?.windowManager?.addCrossWindowBlurEnabledListener { enabled ->
            if (enabled) {
                setBlurredBackground()
            } else {
                Toast.makeText(
                    context,
                    "Cross-window blur is disabled at runtime",
                    Toast.LENGTH_SHORT
                ).show()
                dismiss()
            }
        }
    }


    private fun setBlurredBackground() {
        if (window?.windowManager?.isCrossWindowBlurEnabled == true) {
            val width = context.resources.displayMetrics.widthPixels / 2
            val height = context.resources.displayMetrics.heightPixels / 2
            window?.setLayout(width, height)
//            window?.setFlags(
//                WindowManager.LayoutParams.FLAG_BLUR_BEHIND,
//                WindowManager.LayoutParams.FLAG_BLUR_BEHIND
//            )
            window?.attributes?.blurBehindRadius = blurBehindRadius
            window?.setBackgroundBlurRadius(backgroundBlurRadius)
            window?.setDimAmount(0f)
        }
    }


}
