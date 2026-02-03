package com.rktuhin.themeapp.data

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

class ThemeManager(context: Context) {
    private val prefs = context.getSharedPreferences("theme_settings", Context.MODE_PRIVATE)

    companion object {
        private const val THEME_KEY = "current_theme"

        // Use constants for clarity
        const val LIGHT = 0
        const val DARK = 1
        const val SYSTEM = 2 // This is our "Initial" and "Default" state
    }

    /**
     * Saves the choice and immediately updates the UI
     */
    fun saveAndApplyTheme(themeMode: Int) {
        prefs.edit { putInt(THEME_KEY, themeMode) }
        applyTheme(themeMode)
    }

    /**
     * Maps our internal constants to AppCompatDelegate modes
     */
    fun applyTheme(themeMode: Int) {
        val mode = when (themeMode) {
            LIGHT -> AppCompatDelegate.MODE_NIGHT_NO
            DARK -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    /**
     * Retrieves the theme. Default value is SYSTEM (2)
     */
    fun getSavedTheme(): Int = prefs.getInt(THEME_KEY, SYSTEM)
}