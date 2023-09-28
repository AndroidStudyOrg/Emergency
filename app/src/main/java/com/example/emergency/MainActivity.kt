package com.example.emergency

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.emergency.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goInputActivity.setOnClickListener {
            val intent = Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        getDataUiUpdate()
    }

    private fun getDataUiUpdate() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE)) {
            binding.tvNameValue.text = getString(NAME, "미정")
            binding.tvBirthhDayValue.text = getString(BIRTHDATE, "미정")
            binding.tvBloodTypeValue.text = getString(BLOOD_TYPE, "미정")
            binding.tvCallNumValue.text = getString(EMERGENCY_CONTACT, "미정")
            val warning = getString(WARNING, "미정")

            binding.tvWarning.isVisible = warning.isNullOrEmpty().not()
            binding.tvWarningValue.isVisible = warning.isNullOrEmpty().not()
            if (!warning.isNullOrEmpty()) {
                binding.tvWarningValue.text = warning
            }
        }
    }
}