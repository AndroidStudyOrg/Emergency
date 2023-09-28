package com.example.emergency

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
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

        binding.bntSave.setOnClickListener {
            saveData()
            // 저장 완료 후 Activity 종료
            finish()
        }
    }

    /**
     * Context.MODE_PRIVATE: 파일을 생성한 앱에서만 접근 가능
     */
    private fun saveData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            putString(NAME, binding.etNameValue.text.toString())
            putString(BLOOD_TYPE, getBloodType())
            putString(EMERGENCY_CONTACT, binding.etCallNumValue.text.toString())
            putString(BIRTHDATE, binding.tvBirthhDayValue.text.toString())
            putString(WARNING, getWarning())
            apply()
            /**
             * commit을 해줄 수도, apply를 해줄 수도 있음
             * commit: 얘를 가지고 있는 스레드를 막고, 저장이 완료된 후에 다음 동작을 함. 데이터를 저장하는 동안 사용자는 아무것도 못함
             * apply: commit과는 다르게 비동기적으로 동작함. 저장할 때 해당 스레드가 아닌 다른 스레드를 열어서 작업함. 가능한한 apply로
             */
        }
        Toast.makeText(this, "저장이 완료되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBloodType(): String {
        val bloodType = binding.spinnerBloodType.selectedItem.toString()
        val bloodSign = if (binding.rbPlus.isChecked) "+" else "-"
        return "$bloodSign$bloodType"
    }

    private fun getWarning(): String {
        return if (binding.cbWarning.isChecked) {
            binding.etWarningValue.text.toString()
        } else {
            ""
        }
    }
}