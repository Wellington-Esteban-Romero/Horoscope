package com.horoscope

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.horoscope.data.Horoscope
import com.horoscope.data.HoroscopeProvider
import com.horoscope.utils.SessionManager

/*
* ReclyclerView (Listar)
* SharedPreferences (session)f
* Menús y navegacion
* */
class MainActivity : AppCompatActivity() {

    private lateinit var horoscopeAdapter: HoroscopeAdapter
    private lateinit var horoscopeList: List<Horoscope>
    private lateinit var recycler:RecyclerView

    companion object {
        lateinit var session: SessionManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        session = SessionManager(applicationContext)

        recycler = findViewById(R.id.rvHoroscope)
        horoscopeList = HoroscopeProvider.findAll()

        horoscopeAdapter = HoroscopeAdapter(horoscopeList) { horoscope ->
            onItemSelect(horoscope)
        }

        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = horoscopeAdapter
        }
        getSupportActionBarHoroscope()
    }

    private fun init() {
        recycler = findViewById(R.id.rvHoroscope)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu.findItem(R.id.actionSearch)

        val searchView: SearchView = searchItem.getActionView() as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false;
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText != null) filter(newText)
                return false
            }
        })
        return true
    }

    private fun filter(text: String) {
        val filteredList: ArrayList<Horoscope> = ArrayList()

        for (item in horoscopeList) {
             if (getString(item.name).lowercase().contains(text.lowercase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            //setContentView(R.layout.empty)
            Toast.makeText(this, getText(R.string.no_search), Toast.LENGTH_SHORT).show()
        } else {
            horoscopeAdapter.filterList(filteredList)
        }
    }

    private fun onItemSelect(horoscope: Horoscope) {
        val intent = Intent(this, DetailsHoroscope::class.java)
        intent.putExtra("id", horoscope.id.toString())

        var name = getString(horoscope.name)

        if (session.getHoroscope(name) != SessionManager.ACTIVE)
            session.saveHoroscope(name, SessionManager.DES_ACTIVE)

        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        horoscopeAdapter.notifyDataSetChanged()
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