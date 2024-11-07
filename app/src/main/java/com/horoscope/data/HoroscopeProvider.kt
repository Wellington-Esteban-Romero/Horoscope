package com.horoscope.data

import com.horoscope.R

class HoroscopeProvider {

    companion object {

        private val horoscopes: List<Horoscope> = listOf(
            Horoscope(1, R.string.aries, R.string.date_aries, R.drawable.ic_aries),
            Horoscope(2, R.string.tauro, R.string.date_tauro, R.drawable.ic_taurus),
            Horoscope(3, R.string.geminis, R.string.date_geminis, R.drawable.ic_gemini),
            Horoscope(4, R.string.cancer, R.string.date_cancer, R.drawable.ic_cancer),
            Horoscope(5, R.string.leo, R.string.date_leo, R.drawable.ic_leo),
            Horoscope(6, R.string.virgo, R.string.date_virgo, R.drawable.ic_virgo),
            Horoscope(7, R.string.libra, R.string.date_libra, R.drawable.ic_libra),
            Horoscope(8, R.string.escorpio, R.string.date_escorpio, R.drawable.ic_scorpio),
            Horoscope(9, R.string.sagitario, R.string.date_sagitario, R.drawable.ic_saggittarius),
            Horoscope(10, R.string.capripornio, R.string.date_capripornio, R.drawable.ic_capricornius),
            Horoscope(11, R.string.acuario, R.string.date_acuario, R.drawable.ic_aquarius),
            Horoscope(12, R.string.piscis, R.string.date_piscis, R.drawable.ic_psices)
        )

        fun findAll(): List<Horoscope> { return horoscopes; }

        fun findById(id: Int): Horoscope {
            return horoscopes.stream()
                .filter { h -> h.id == id }
                .findFirst()
                .orElseThrow()
        }
    }
}