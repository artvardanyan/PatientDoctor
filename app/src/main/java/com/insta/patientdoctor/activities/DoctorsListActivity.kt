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
    var doc: String? = null
    var deg: kotlin.String? = null
    var photo: kotlin.String? = null
    var number: kotlin.String? = null
    var recyclerView: RecyclerView? = null
    var listView: ListView? = null
    var myAdapter: MyAdapter? = null

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

        val prob = bundle!!.getString("dept")
        val token = bundle!!.getInt("token")
        val name = bundle!!.getString("pname")
        val phone = bundle!!.getString("phone")
//        Intent i = new Intent(RecyclerView.this,PatientActivity.class);
//        Bundle bundle1 = new Bundle();
//        bundle1.putString("pname",name);
//        bundle1.putString("phone",phone);
//        i.putExtras(bundle1);
//        startActivity(i);
        //String tok = Integer.toString(token);

        //        Intent i = new Intent(RecyclerView.this,PatientActivity.class);
//        Bundle bundle1 = new Bundle();
//        bundle1.putString("pname",name);
//        bundle1.putString("phone",phone);
//        i.putExtras(bundle1);
//        startActivity(i);
        //String tok = Integer.toString(token);
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