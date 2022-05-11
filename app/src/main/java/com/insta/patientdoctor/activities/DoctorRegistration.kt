package com.insta.patientdoctor.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.insta.patientdoctor.R
import com.insta.patientdoctor.databinding.ActivityDoctorRegistrationBinding
import com.insta.patientdoctor.viewmodel.DoctorViewModel
import com.insta.patientdoctor.viewmodel.PatientViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DoctorRegistration : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var viewModel: DoctorViewModel
    private lateinit var binding: ActivityDoctorRegistrationBinding

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.title = "Doctor Registration"

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DoctorViewModel::class.java]

        viewModel.createDataBase(this)

        viewModel._listUsers.observe(this, Observer {
            if (it != null) {
                // put your logic here
            }
        })

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        val department = arrayOf(
            "Choose Department",
            "General Medicine",
            "Cardiology",
            "Oncology",
            "Dental Medicine",
            "Opthamology",
            "Orthopaedics"
        )

        val spinnerArrayAdapter = ArrayAdapter(
            this, R.layout.spinner_item, department
        )

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)

        binding.spin.adapter = spinnerArrayAdapter

        binding.submit.setOnClickListener {

            binding.gender.setOnCheckedChangeListener { radioGroup, i ->
                val radioButton = radioGroup.findViewById<RadioButton>(i)
                val gender = radioButton.text.toString()
            }

            val n = binding.name.text.toString().trim()
            val id = binding.did.text.toString().trim()
            val a = binding.age.text.toString().trim()
            val add = binding.address.text.toString().trim()
            val ph = binding.phone.text.toString().trim()

            when {
                n.isEmpty() -> {
                    binding.name.error = "Please enter Name"
                }
                id.isEmpty() -> {
                    binding.did.error = "Please fill ID"
                }
                a.isEmpty() -> {
                    binding.age.error = "Please enter Age"
                }
                add.isEmpty() -> {
                    binding.address.error = "Please enter Address"
                }
                ph.isEmpty() -> {
                    binding.phone.error = "Please enter Phone Number"
                }
                else -> {

                    lifecycleScope.launch(Dispatchers.Default) {

                        viewModel.getDbUsers()

                        withContext(Dispatchers.Main) {
                            Toast.makeText(this@DoctorRegistration, "sdds", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("Not yet implemented")
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        var i = 1
    }
}