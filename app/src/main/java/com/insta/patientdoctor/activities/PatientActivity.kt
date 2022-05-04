package com.insta.patientdoctor.activities

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.insta.patientdoctor.BuildConfig
import com.insta.patientdoctor.R
import com.insta.patientdoctor.adapters.MyAdapter
import com.insta.patientdoctor.databinding.ActivityPatientBinding
import com.insta.patientdoctor.databinding.SpinnerItemBinding
import com.insta.patientdoctor.modals.DoctorDetails
import java.util.*

class PatientActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPatientBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Objects.requireNonNull(supportActionBar)!!.title = "E-Token Confirmation"
        val b = intent.extras

        Toast.makeText(this, "Doctor Allocated Successfully", Toast.LENGTH_SHORT).show()
        if (BuildConfig.DEBUG && b == null) {
            error("Assertion failed")
        }
        val token = b?.getInt("Token")
        val name = b?.getString("Name")
        val qual = b?.getString("Qual")
        val url = b?.getString("Url")
        val ph = b?.getString("phone")
        val tok = token.toString()
        val msg =
            "Dear ,\nWelcome to ABC Hospitals,\nYou are assigned to $name,\n\nAverage Waiting Time is 15min"

        binding.docname.text = name
        binding.degree.text = qual
        binding.tokenno.text = tok
        binding.tokennumber.text = ph
        Glide.with(applicationContext)
            .load(R.drawable.docname)
            .into(binding.doctor)
        binding.time.text = "30 min.*"
        //binding.msg.text = msg

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}