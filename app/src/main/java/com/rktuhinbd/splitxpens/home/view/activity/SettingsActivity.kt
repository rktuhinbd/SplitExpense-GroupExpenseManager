package com.rktuhinbd.splitxpens.home.view.activity

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.rktuhinbd.splitxpens.databinding.ActivitySettingsBinding
import com.rktuhinbd.splitxpens.utils.MyPreferences

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Code to set your switch button according to your selection
        binding.switchTheme.isChecked = MyPreferences(this@SettingsActivity).darkMode == 1

        setupClickListeners()
    }

    private fun setupClickListeners() {

        binding.switchTheme.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                MyPreferences(this).darkMode = 1  //This line will store dark mode in shared preference
                delegate.applyDayNight()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                MyPreferences(this).darkMode = 0  //This line will store light mode in shared preference
                delegate.applyDayNight()
            }
        }
    }
}