package com.dicoding.picodiploma.loginwithanimation.view.scanqr

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.loginwithanimation.R

class ScanQRDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.scanqr_detail)

        val detailText = intent.getStringExtra("QR_RESULT")
        findViewById<TextView>(R.id.tvDetail).text = detailText
    }
}
