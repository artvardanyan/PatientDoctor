package com.insta.patientdoctor.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.insta.patientdoctor.R
import com.insta.patientdoctor.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.patient.setOnClickListener {
            val intent = Intent(this@MainActivity, PatientRegistration::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        binding.doctor.setOnClickListener {
            val intent = Intent(this@MainActivity, DoctorRegistration::class.java)
            //intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_item, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item1 -> {
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item2 -> {
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.item3 -> {
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem1 -> {
                Toast.makeText(this, "Sub Item 1 selected", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.subitem2 -> {
                Toast.makeText(this, "Sub Item 2 selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}