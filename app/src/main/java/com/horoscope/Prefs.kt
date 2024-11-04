package com.horoscope

import android.content.Context

class Prefs(context: Context) {

    companion object {
        const val ACTIVE = "1"
        const val DESACTIVE = "0"
    }

    private val SHARED_NAME = "Mydtb"
    private val SHARED_NAME_HOROSCOPE = "horoscope"

    private val storage = context.getSharedPreferences(SHARED_NAME, 0);

    fun saveName (key:String ,value:String) {
        storage.edit().putString(SHARED_NAME_HOROSCOPE + key, value).apply()
    }

    fun getName (key:String):String {
        return storage.getString(SHARED_NAME_HOROSCOPE + key, "")!!
    }
}