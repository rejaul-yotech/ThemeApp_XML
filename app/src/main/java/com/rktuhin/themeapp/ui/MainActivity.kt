package com.rktuhin.themeapp.ui

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rktuhin.themeapp.data.ThemeManager
import com.rktuhin.themeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        themeManager = ThemeManager(this)

        // 1. Critical: Apply theme BEFORE super.onCreate
        // If it's the first run, this will follow System Default.
        themeManager.applyTheme(themeManager.getSavedTheme())

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()
        setupListeners()
    }

    private fun initUI() {
        // Sync the RadioButtons with the stored preference
        when (themeManager.getSavedTheme()) {
            ThemeManager.LIGHT -> binding.radioLight.isChecked = true
            ThemeManager.DARK -> binding.radioDark.isChecked = true
            ThemeManager.SYSTEM -> binding.radioDefault.isChecked = true
        }
    }

    private fun setupListeners() {
        binding.themeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedMode = when (checkedId) {
                binding.radioLight.id -> ThemeManager.LIGHT
                binding.radioDark.id -> ThemeManager.DARK
                else -> ThemeManager.SYSTEM
            }

            // Save to SharedPreferences and Apply
            themeManager.saveAndApplyTheme(selectedMode)

            // Modern transition to avoid flickering
            applySmoothTransition()
        }
    }

    private fun applySmoothTransition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(
                OVERRIDE_TRANSITION_OPEN,
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
        } else {
            @Suppress("DEPRECATION")
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        }
    }
}