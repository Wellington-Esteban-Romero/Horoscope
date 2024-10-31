package com.horoscope

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.horoscope.data.Horoscope
import com.horoscope.data.HoroscopeProvider


class HoroscopeDetail : AppCompatActivity() {

    companion object {
        const val MyPREFERENCES: String = "MyPrefs"
    }

    private lateinit var txtViewNameHoroscope: TextView
    private lateinit var imgHoroscope: ImageView
    private lateinit var txtDateHoroscope: TextView
    private lateinit var actionFavorite: View
    private var contador:Int = 0

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


         var sharedPreferences:SharedPreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE)



        //val myEdit: SharedPreferences.Editor = sharedPreferences.edit()

        //myEdit.putString("name", name.getText().toString())

        getSupportActionBarHoroscope ()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.favorite_menu, menu)
        //val favoriteItem: MenuItem = menu.findItem(R.id.actionFavorite)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        System.out.println(item.icon)
        item.setIcon(R.drawable.ic_favorite)
        return super.onOptionsItemSelected(item)
    }

    private fun init() {
        txtViewNameHoroscope = findViewById(R.id.txtViewNameHoroscope)
        imgHoroscope = findViewById(R.id.imgHoroscope)
        txtDateHoroscope = findViewById(R.id.txtViewDateHoroscope)
        //actionFavorite = findViewById(R.id.actionFavorite)
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

        val colorDrawable = ColorDrawable(getResources().getColor(R.color.menu_color, null))
        supportActionBar!!.setBackgroundDrawable(colorDrawable)
    }
}