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
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.tasks.Task
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateLanguage
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.TranslatorOptions
import com.horoscope.MainActivity.Companion.session
import com.horoscope.data.Horoscope
import com.horoscope.data.HoroscopeProvider
import com.horoscope.data.HoroscopeServiceFactory
import com.horoscope.utils.SessionManager
import com.horoscope.utils.Utils
import kotlinx.coroutines.launch

class DetailsHoroscope : AppCompatActivity() {

    private lateinit var txtViewNameHoroscope: TextView
    private lateinit var txtViewDataHoroscope: TextView
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

        val service = HoroscopeServiceFactory.getRetrofitService()

        lifecycleScope.launch {
           val horoscope = service.getHoroscope(Utils.getHoroscopeEnglish(horoscope.id), "TODAY")
            /*val options:TranslatorOptions = TranslatorOptions.Builder()
                .setTargetLanguage(TranslateLanguage.ENGLISH)
                .setTargetLanguage(TranslateLanguage.SPANISH)
                .build()
            val englishSpanishTranslator = Translation.getClient(options)
            var data:String =  horoscope.data.horoscope_data
            var task:Task<String> = englishSpanishTranslator.translate(data).addOnSuccessListener { translatedText ->
                txtViewDataHoroscope.text =  translatedText
            }.addOnFailureListener { exception ->
                txtViewDataHoroscope.text =  "ERROR"
            }
           val modelManager = RemoteModelManager.getInstance()
            var conditions = DownloadConditions.Builder()
                .requireWifi()
                .build()
            englishSpanishTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener {
                    // Model downloaded successfully. Okay to start translating.
                    // (Set a flag, unhide the translation UI, etc.)
                }
                .addOnFailureListener { exception ->
                    // Model couldn’t be downloaded or other internal error.
                    // ...
                }*/

            txtViewDataHoroscope.text =  horoscope.data.horoscope_data
        }

        getSupportActionBarHoroscope ()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.details_menu, menu)

        var name = getString(horoscope.name)

        if (session.isFavorite(name))
            menu.findItem(R.id.actionFavorite).setIcon(R.drawable.ic_favorite)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        println(item.icon)

        when (item.itemId) {
            R.id.actionFavorite -> {
                var name = getString(horoscope.name)

                if (!session.isFavorite(name)) {
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
        txtViewDataHoroscope = findViewById(R.id.txtViewDataHoroscope)
        imgHoroscope = findViewById(R.id.imgHoroscope)
        //txtDateHoroscope = findViewById(R.id.txtViewDateHoroscope)
    }

    private fun getHoroscope ():Horoscope {
        return HoroscopeProvider.findById(intent.getStringExtra("id")!!.toInt())
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