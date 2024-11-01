package com.example.duanmau_quanhqph33420.Login_

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.example.duanmau_quanhqph33420.R

class manHinhChao : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chao)
        val timer = object : CountDownTimer(2000, 1000) {
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                val intent = Intent(this@manHinhChao, dangNhap::class.java)
                startActivity(intent)
            }

        }
        timer.start()
    }
}