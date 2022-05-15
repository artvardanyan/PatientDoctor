package com.insta.patientdoctor.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.insta.patientdoctor.R
import com.insta.patientdoctor.databinding.ActivityPatientRegistrationBinding
import com.insta.patientdoctor.viewmodel.PatientViewModel
import kotlinx.coroutines.*

class PatientRegistration : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityPatientRegistrationBinding
    private lateinit var viewModel: PatientViewModel

    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[PatientViewModel::class.java]

        viewModel.createDataBase(this)

        viewModel._listUsers.observe(this, {
//            if (it != null) {
//
//            }
        })

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        this.title = "Patient Registration"

        val spinner = findViewById<Spinner>(R.id.spin)

        val department = arrayOf(
            "Choose Problem",
            "General Medicine",
            "Cardiology",
            "Oncology",
            "Dental Medicine",
            "Ophthalmology",
            "Orthopaedics"
        )

        val spinnerArrayAdapter = ArrayAdapter(
            this, R.layout.spinner_item, department
        )
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        spinner.adapter = spinnerArrayAdapter

        binding.submit.setOnClickListener {
            binding.gendercheck.setOnCheckedChangeListener { radioGroup, i ->
                val radioButton = radioGroup.findViewById<RadioButton>(i)
                radioButton.text.toString()
            }

            val n = binding.name.text.toString().trim()
            val id = binding.pid.text.toString().trim()
            val a = binding.age.text.toString().trim()
            val add = binding.address.text.toString().trim()
            val dept = binding.spin.selectedItem.toString()
            val ph = binding.phone.text.toString().trim()

            when {
                n.isEmpty() -> {
                    binding.name.error = "Please enter Name"
                }
                id.isEmpty() -> {
                    binding.pid.error = "Please fill ID"
                }
                a.isEmpty() -> {
                    binding.age.error = "Please enter Age"
                }
            }

            when {
                add.isEmpty() -> {
                    binding.address.error = "Please enter Address"
                }
                ph.isEmpty() -> {
                    binding.phone.error = "Please enter Phone Number"
                }
                ph.length != 10 -> {
                    binding.phone.error = "Phone Number is Invalid"
                }
                dept == "Choose Problem" -> {
                    Toast.makeText(this@PatientRegistration, "Choose Problem ", Toast.LENGTH_LONG)
                        .show()
                }
                else -> {
                    lifecycleScope.launch(Dispatchers.Default) {

                        viewModel.getDbUsers()

                        withContext(Dispatchers.Main) {
                            val intent =
                                Intent(this@PatientRegistration, PatientActivity::class.java)
                            intent.putExtra("Token", 6)
                            intent.putExtra("Name", "Arthur")
                            intent.putExtra("Qual", "Qual")
                            intent.putExtra("Url", "Url")
                            intent.putExtra("phone", "095101113")
                            startActivity(intent)
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
}