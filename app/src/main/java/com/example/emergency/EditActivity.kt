package com.example.emergency

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import com.example.emergency.databinding.ActivityEditBinding

class EditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spinnerBloodType.adapter = ArrayAdapter.createFromResource(
            this, R.array.blood_types, android.R.layout.simple_list_item_1
        )

        binding.birthDatelayer.setOnClickListener {
            val listener = OnDateSetListener { _, year, month, dayofMonth ->
                binding.tvBirthhDayValue.text = "$year-${month.inc()}-$dayofMonth"
            }
            DatePickerDialog(
                this, listener, 2000, 1, 1
            ).show()
        }

        binding.cbWarning.setOnCheckedChangeListener { _, isChecked ->
            binding.etWarningValue.isVisible = isChecked
        }

        binding.etWarningValue.isVisible = binding.cbWarning.isChecked
    }
}