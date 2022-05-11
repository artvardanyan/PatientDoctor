package com.insta.patientdoctor.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.insta.patientdoctor.R
import com.insta.patientdoctor.databinding.ActivityPatientBinding
import com.insta.patientdoctor.databinding.ActivitySelectDoctorBinding
import java.util.*

class SelectDoctor : AppCompatActivity() {

    private lateinit var binding: ActivitySelectDoctorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectDoctorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Objects.requireNonNull(supportActionBar)!!.title = "Select Doctor"

        val nam = binding.docname.text.toString().trim { it <= ' ' }
        val qual = binding.degree.text.toString().trim { it <= ' ' }

        binding.select.setOnClickListener {
            val i = Intent(this@SelectDoctor, PatientActivity::class.java)
            val b = Bundle()
            b.putString("Name", nam)
            b.putString("Qual", qual)
            i.putExtras(b)
            startActivity(i)
        }
    }
}