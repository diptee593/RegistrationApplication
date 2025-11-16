package com.example.registrationapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DisplayActivity : AppCompatActivity() {

    private lateinit var etNameDisplay: EditText
    private lateinit var etDobDisplay: EditText
    private lateinit var etEmailDisplay: EditText
    private lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        etNameDisplay = findViewById(R.id.etNameDisplay)
        etDobDisplay = findViewById(R.id.etDobDisplay)
        etEmailDisplay = findViewById(R.id.etEmailDisplay)
        btnBack = findViewById(R.id.btnBack)

        val name = intent.getStringExtra("name") ?: ""
        val dob = intent.getStringExtra("dob") ?: ""
        val email = intent.getStringExtra("email") ?: ""

        etNameDisplay.setText(name)
        etDobDisplay.setText(dob)
        etEmailDisplay.setText(email)

        btnBack.setOnClickListener { finish() }
    }
}
