package com.horoscope

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.horoscope.data.Horoscope
import com.horoscope.data.HoroscopeProvider

class HoroscopeDetail : AppCompatActivity() {

    private lateinit var txtViewNameHoroscope: TextView
    private lateinit var imgHoroscope: ImageView
    private lateinit var txtDateHoroscope: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horoscope_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        var horoscope:Horoscope = getHoroscope();

        txtViewNameHoroscope.setText(horoscope.name)
        imgHoroscope.setImageResource(horoscope.image)
        txtDateHoroscope.setText(horoscope.date)

        getSupportActionBarHoroscope ()
    }

    private fun init() {
        txtViewNameHoroscope = findViewById(R.id.txtViewNameHoroscope)
        imgHoroscope = findViewById(R.id.imgHoroscope)
        txtDateHoroscope = findViewById(R.id.txtViewDateHoroscope)
    }

    private fun getHoroscope ():Horoscope {
        var id = intent.getStringExtra("id")
        return HoroscopeProvider.findById(id!!.toInt())
    }

    private fun getSupportActionBarHoroscope () {
        var supportActionBar = supportActionBar;
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setLogo(R.drawable.ic_zodiac);
        supportActionBar?.setDisplayUseLogoEnabled(true);
    }
}