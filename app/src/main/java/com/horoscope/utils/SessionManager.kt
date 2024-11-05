package com.horoscope.utils

import android.content.Context

class SessionManager(context: Context) {

    companion object {
        const val ACTIVE = "1"
        const val DES_ACTIVE = "0"
    }

    private val SHARED_NAME = "Mydtb"
    private val SHARED_NAME_HOROSCOPE = "horoscope"

    private val storage = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);

    fun saveHoroscope (key:String ,value:String) {
        storage.edit().putString(SHARED_NAME_HOROSCOPE + key, value).apply()
    }

    fun getHoroscope (key:String):String {
        return storage.getString(SHARED_NAME_HOROSCOPE + key, "")!!
    }

    fun isFavorite (id:String):Boolean {
        return (getHoroscope (id) == ACTIVE)
    }
}