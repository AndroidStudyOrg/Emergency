package com.example.emergency

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class EditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        val message = intent.getStringExtra("intentMessage") ?: "없음"
        Log.d("intentMessage", message)
    }
}