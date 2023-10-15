package com.example.vtb_hackathon.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.vtb_hackathon.domain.repository.SharedPreferencesRepository

class SharedPreferencesRepositoryImpl(private val context: Context) : SharedPreferencesRepository {
    private val appPrefs: SharedPreferences
        get() = context
            .getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE)

    private val prefsEditor: SharedPreferences.Editor
        get() = context
            .getSharedPreferences(APP_PREFS_NAME, Context.MODE_PRIVATE).edit()

    override suspend fun updatePrefs(key: String, valueAny: Any) {
        when (valueAny) {
            is String -> {
                updatePrefs(key, valueAny)
            }
            is Long -> {
                updatePrefs(key, valueAny)
            }
            is Int -> {
                updatePrefs(key, valueAny)
            }
            is Double -> {
                updatePrefs(key, valueAny)
            }
            is Float -> {
                updatePrefs(key, valueAny)
            }
            is Boolean -> {
                updatePrefs(key, valueAny)
            }
        }
    }

    private fun updatePrefs(key: String, value: Double) {
        prefsEditor.putLong(key, java.lang.Double.doubleToRawLongBits(value)).apply()
    }

    private fun updatePrefs(key: String, valueStr: String) {
        prefsEditor.putString(key, valueStr).apply()
    }

    private fun updatePrefs(key: String, valueBol: Boolean) {
        prefsEditor.putBoolean(key, valueBol).apply()
    }

    private fun updatePrefs(key: String, valueFloat: Float) {
        prefsEditor.putFloat(key, valueFloat).apply()
    }

    private fun updatePrefs(key: String, valueInt: Int) {
        prefsEditor.putInt(key, valueInt).apply()
    }

    private fun updatePrefs(key: String, valueLong: Long) {
        prefsEditor.putLong(key, valueLong).apply()
    }

    override suspend fun getStringPrefs(strName: String): String {
        return appPrefs.getString(strName, "").toString()
    }

    override suspend fun getBoolPrefs(strName: String): Boolean {
        return appPrefs.getBoolean(strName, false)
    }


    override suspend fun getIntPrefs(strName: String): Int {
        return appPrefs.getInt(strName, 0)
    }

    override suspend fun getLongPrefs(strName: String): Long {
        return appPrefs.getLong(strName, 0)
    }

    override suspend fun getDoublePrefs(key: String): Double {
        return java.lang.Double.longBitsToDouble(
            appPrefs.getLong(
                key,
                java.lang.Double.doubleToLongBits(0.0)
            )
        )
    }

    companion object {
        private const val APP_PREFS_NAME = "appPrefsName"
    }
}