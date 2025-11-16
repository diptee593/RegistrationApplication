package com.example.registrationapplication

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etDob: EditText
    private lateinit var etEmail: EditText
    private lateinit var btnSubmit: Button

    companion object {
        // In-memory list (non-persistent)
        val inMemoryUsers = ArrayList<User>()
    }

    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etDob = findViewById(R.id.etDob)
        etEmail = findViewById(R.id.etEmail)
        btnSubmit = findViewById(R.id.btnSubmit)

        dbHelper = DatabaseHelper(this)

        etDob.setOnClickListener { openDatePicker() }

        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val dob = etDob.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (!validateInputs(name, dob, email)) return@setOnClickListener

            // Save in memory
            val user = User(name, dob, email)
            inMemoryUsers.add(user)

            // Save in SQLite
            val id = dbHelper.insertUser(name, dob, email)
            if (id == -1L) {
                Toast.makeText(this, "Database insert failed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Saved (DB id: $id)", Toast.LENGTH_SHORT).show()
            }

            // Pass to DisplayActivity
            val intent = Intent(this@MainActivity, DisplayActivity::class.java).apply {
                putExtra("name", name)
                putExtra("dob", dob)
                putExtra("email", email)
            }
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun openDatePicker() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, { _, y, m, d ->
            val m1 = m + 1
            val dayStr = if (d < 10) "0$d" else "$d"
            val monthStr = if (m1 < 10) "0$m1" else "$m1"
            etDob.setText("$dayStr-$monthStr-$y")
        }, year, month, day)
        dpd.show()
    }

    private fun validateInputs(name: String, dob: String, email: String): Boolean {
        if (TextUtils.isEmpty(name)) {
            etName.error = "Enter name"
            return false
        }
        if (TextUtils.isEmpty(dob)) {
            etDob.error = "Enter date of birth"
            return false
        }
        if (TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.error = "Enter a valid email"
            return false
        }
        return true
    }
}
