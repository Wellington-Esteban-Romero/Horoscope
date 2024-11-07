package com.horoscope.utils


class Utils {

    companion object {
        fun getHoroscopeEnglish(id: Int): String {
            val horoscope = listOf(
                "Aries",
                "Taurus",
                "Gemini",
                "Cancer",
                "Leo",
                "Virgo",
                "Libra",
                "Scorpio",
                "Sagittarius",
                "Capricorn",
                "Aquarius",
                "Pisces"
            )
            return horoscope.get(id - 1)
        }
    }
}