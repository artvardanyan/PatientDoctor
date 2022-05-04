package com.insta.patientdoctor.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.insta.patientdoctor.R
import java.util.*

class SelectDoctor : AppCompatActivity() {

    var name: TextView? = null
    var degree:TextView? = null
    var phone:TextView? = null
    var docimage: ImageView? = null
    var select: Button? = null
    var nam: String? = null
    var qual:kotlin.String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_doctor)


        Objects.requireNonNull(supportActionBar)!!.title = "Select Doctor"
        name = findViewById<View>(R.id.docname) as TextView
        degree = findViewById<View>(R.id.degree) as TextView
        phone = findViewById<View>(R.id.phone) as TextView
        docimage = findViewById<View>(R.id.image) as ImageView

        select = findViewById<View>(R.id.select) as Button

        nam = name!!.text.toString().trim { it <= ' ' }
        qual = degree!!.text.toString().trim { it <= ' ' }

        select!!.setOnClickListener {
            val i = Intent(this@SelectDoctor, PatientActivity::class.java)
            val b = Bundle()
            b.putString("Name", nam)
            b.putString("Qual", qual)
            i.putExtras(b)
            startActivity(i)
            //startActivity(new Intent(getApplicationContext(),PatientActivity.class));
        }

        /*Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.);
        docimage.setImageBitmap(icon);*/



    }
}