package com.insta.patientdoctor.activities

import android.app.ProgressDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.insta.patientdoctor.R
import com.insta.patientdoctor.adapters.MyAdapter
import java.util.*

class DoctorsListActivity : AppCompatActivity() {

    var databaseReference: DatabaseReference? = null
    var recyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctors_list)

        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()

        val editText = findViewById<EditText>(R.id.edittext)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                filter(s.toString())
            }
        })

        recyclerView = findViewById<View>(R.id.myrecycler) as RecyclerView

        recyclerView!!.layoutManager = LinearLayoutManager(this)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }

        val bundle = intent.extras!!
        val prob = bundle.getString("dept")

        databaseReference =
            FirebaseDatabase.getInstance().reference.child("Doctor_Details").child(prob!!)
    }

    private fun filter(text: String) {

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}