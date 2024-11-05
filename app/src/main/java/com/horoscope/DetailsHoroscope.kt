package com.horoscope

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.horoscope.MainActivity.Companion.session
import com.horoscope.data.Horoscope
import com.horoscope.data.HoroscopeProvider
import com.horoscope.utils.SessionManager

class DetailsHoroscope : AppCompatActivity() {

    private lateinit var txtViewNameHoroscope: TextView
    private lateinit var imgHoroscope: ImageView
    private lateinit var txtDateHoroscope: TextView
    private lateinit var horoscope: Horoscope

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horoscope_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        horoscope = getHoroscope();

        txtViewNameHoroscope.setText(horoscope.name)
        imgHoroscope.setImageResource(horoscope.image)
        txtDateHoroscope.setText(horoscope.date)

        getSupportActionBarHoroscope ()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.details_menu, menu)

        var name = getString(horoscope.name)

        if (session.getHoroscope(name) == SessionManager.ACTIVE)
            menu.findItem(R.id.actionFavorite).setIcon(R.drawable.ic_favorite)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println(item.icon)

        when (item.itemId) {
            R.id.actionFavorite -> {
                var name = getString(horoscope.name)

                if (session.getHoroscope(name) == SessionManager.DES_ACTIVE) {
                    session.saveHoroscope(name, SessionManager.ACTIVE)
                    item.setIcon(R.drawable.ic_favorite)
                } else {
                    session.saveHoroscope(name, SessionManager.DES_ACTIVE)
                    item.setIcon(R.drawable.ic_favorite_empty)
                }
            }
            R.id.actionShare -> {
                share()
            }
        }
        return super.onOptionsItemSelected(item)
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

    private fun share () {
        val intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "SUSCRIBETE")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }

    /*private fun shareImg () {
        val uriArray:ArrayList<Uri> = arrayListOf(Uri.EMPTY, Uri.EMPTY)
        val intent = Intent().apply {
            action = Intent.ACTION_SEND_MULTIPLE
            putExtra(Intent.EXTRA_STREAM, uriArray)
            type = "img/jpeg"
        }

        val shareIntent = Intent.createChooser(intent, null)
        startActivity(shareIntent)
    }*/

    private fun getSupportActionBarHoroscope () {
        var supportActionBar = supportActionBar;
        supportActionBar?.setDisplayShowHomeEnabled(true);
        supportActionBar?.setLogo(R.drawable.ic_zodiac);
        supportActionBar?.setDisplayUseLogoEnabled(true);

        val colorDrawable = ColorDrawable(getResources().getColor(R.color.menu_color, null))
        supportActionBar!!.setBackgroundDrawable(colorDrawable)
    }

    override fun onBackPressed() {
            super.onBackPressed()
    }
}