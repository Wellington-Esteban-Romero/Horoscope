package com.horoscope

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.horoscope.data.HoroscopeProvider

class HoroscopeDetail : AppCompatActivity() {

    private lateinit var txtNameHoroscope: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horoscope_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtNameHoroscope = findViewById(R.id.txtViewHoroscope)

        var id = intent.getStringExtra("id")

        var horoscope = HoroscopeProvider.findById(id!!.toInt())

        txtNameHoroscope.setText(horoscope.name)

    }
}