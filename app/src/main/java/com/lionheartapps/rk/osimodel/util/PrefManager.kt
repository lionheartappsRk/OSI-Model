package com.lionheartapps.rk.osimodel.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class PrefManager() {

    private val PREF_NAME = "Lionheartapps- Welcome"
    private val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
    var pref: SharedPreferences? = null
    var editor: Editor? = null
    var _context: Context? = null

    // shared pref mode
    var PRIVATE_MODE = 0


    fun PrefManager(context: Context?) {
        _context = context
        pref = _context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref?.edit();
    }

    fun isFirstTimeLaunch(): Boolean {
        return pref!!.getBoolean(IS_FIRST_TIME_LAUNCH, true)
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        editor!!.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
        editor!!.commit()
    }
}