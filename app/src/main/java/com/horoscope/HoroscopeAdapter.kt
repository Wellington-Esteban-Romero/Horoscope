package com.horoscope

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.horoscope.data.Horoscope
import com.horoscope.utils.SessionManager

class HoroscopeAdapter(
    private var horoscopes: List<Horoscope>,
    private val onClickListener: (Horoscope) -> Unit
) : RecyclerView.Adapter<HoroscopeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HoroscopeViewHolder {
        return HoroscopeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_horoscopo, parent, false)
        )
    }

    fun filterHoroscope(horoscope: List<Horoscope>) {
        this.horoscopes = horoscope;
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: HoroscopeViewHolder, position: Int) {
        holder.render(horoscopes[position], onClickListener)
    }

    override fun getItemCount() = horoscopes.size
}

class HoroscopeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val txtName = view.findViewById<TextView>(R.id.txtHorosocpe)
    private val dateHoroscope = view.findViewById<TextView>(R.id.dateHoroscope)
    private val imgHoroscope = view.findViewById<ImageView>(R.id.imgHoroscope)
    private val favoriteImageView = view.findViewById<ImageView>(R.id.imgFavorite)

    fun render(horoscope: Horoscope, onClickListener: (Horoscope) -> Unit) {
        val context = itemView.context
        txtName.text = context.getString(horoscope.name)
        dateHoroscope.text = context.getString(horoscope.date)
        imgHoroscope.setImageResource(horoscope.image)
        itemView.setOnClickListener {
            onClickListener(horoscope)
        }

        if (SessionManager(context).isFavorite(txtName.text.toString()))
            favoriteImageView.visibility = View.VISIBLE
        else
            favoriteImageView.visibility = View.GONE
    }
}
