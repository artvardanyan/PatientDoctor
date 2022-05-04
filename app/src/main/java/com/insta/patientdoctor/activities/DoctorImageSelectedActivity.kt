package com.insta.patientdoctor.activities

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.insta.patientdoctor.R
import com.insta.patientdoctor.databinding.ActivityDoctorImageSelectedBinding
import java.io.IOException
import java.util.*

class DoctorImageSelectedActivity : AppCompatActivity() {

    var doc: ImageView? = null
    var upload: Button? = null
    var submit: android.widget.Button? = null
    var storage: FirebaseStorage? = null
    var storageReference: StorageReference? = null
    var name: TextView? = null
    var deg: TextView? = null
    var dept: TextView? = null
    var phone: TextView? = null
    private val PICK_IMAGE_REQUEST = 22
    var databaseReference: DatabaseReference? = null
    var firebaseDatabase: FirebaseDatabase? = null
    private var filePath: Uri? = null
    var i = 0
    private lateinit var binding: ActivityDoctorImageSelectedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorImageSelectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.title = "Doctor Profile"
        doc = findViewById<View>(R.id.docimage) as ImageView
        upload = findViewById<View>(R.id.upload) as Button
        submit = findViewById<View>(R.id.submit) as Button
        name = findViewById<View>(R.id.doctorname) as TextView
        deg = findViewById<View>(R.id.doctordegree) as TextView
        dept = findViewById<View>(R.id.doctordept) as TextView
        phone = findViewById<View>(R.id.doctorphone) as TextView

        val bundle = intent.extras

        val nam = bundle!!.getString("Name")
        val degree = bundle.getString("Qualif")
        val gen = bundle.getString("Gender")
        val age = bundle.getString("age")
        val add = bundle.getString("add")
        val phon = bundle.getString("phone")
        val dep = bundle.getString("dept")

        databaseReference = FirebaseDatabase.getInstance().reference.child("Doctor Details")
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

//        firebaseDatabase = FirebaseDatabase.getInstance();
//        var databaseReference: DatabaseReference =
//            firebaseDatabase!!.getReference("Doctor_Details").child(
//                dep.toString()
//            ).child(doc.toString())
//
//        var doctorDetails: DoctorDetails =
//            DoctorDetails(nam.toString(), degree.toString(), add.toString(), dept.toString())
//        databaseReference.setValue(doctorDetails)


        /*firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Doctor_Details").child(dep).child(doc);
        DoctorDetails doctorDetails = new DoctorDetails(nam,degree,,a,add,ph,dept,);
        databaseReference.setValue(doctorDetails);*/

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        name?.text = nam
        deg?.text = degree
        dept?.text = dep
        phone?.text = phon


        upload!!.setOnClickListener {
            selectImage()
            uploadImage();
        }

        submit?.setOnClickListener { uploadImage() }

    }

    private fun selectImage() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(
            Intent.createChooser(
                intent,
                "Select Image from here..."
            ),
            PICK_IMAGE_REQUEST
        )
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(
            requestCode,
            resultCode,
            data
        )

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.data != null) {

            filePath = data.data
            try {

                val bitmap = MediaStore.Images.Media
                    .getBitmap(
                        contentResolver,
                        filePath
                    )
                doc!!.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage() {
        if (filePath != null) {
            val bundle = intent.extras
            val nam = bundle!!.getString("Name")
            val degree = bundle.getString("Qualif")
            val gen = bundle.getString("Gender")
            val age = bundle.getString("age")
            val add = bundle.getString("add")
            val phon = bundle.getString("phone")
            val dep = bundle.getString("dept")
            val no = bundle.getString("id")
            val progressDialog = ProgressDialog(this)
            progressDialog.setTitle("Uploading...")
            progressDialog.show()

            val ref = storageReference!!.child("Images/" + UUID.randomUUID().toString())

            ref.putFile(filePath!!)
                .addOnSuccessListener { taskSnapshot ->
                    val uriTask = taskSnapshot.storage.downloadUrl
                    while (!uriTask.isSuccessful);
                    val downloadUrl = uriTask.result
                    firebaseDatabase = FirebaseDatabase.getInstance()
                    val databaseReference = firebaseDatabase!!.getReference("Doctor_Details").child(
                        dep!!
                    ).child(no!!)

                    //String uploadid = databaseReference.push().getKey();
                    //databaseReference.child("Doctor_Details").child(depart).child(no).setValue(doctorDetails);
                    progressDialog.dismiss()
                    Toast.makeText(
                        this@DoctorImageSelectedActivity,
                        "Doctor Registered Successfully!!",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                }
                .addOnFailureListener { e -> // Error, Image not uploaded
                    progressDialog.dismiss()
                    Toast
                        .makeText(
                            this@DoctorImageSelectedActivity,
                            "Failed " + e.message,
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
                .addOnProgressListener { taskSnapshot ->

                    val progress = (100.0
                            * taskSnapshot.bytesTransferred
                            / taskSnapshot.totalByteCount)
                    progressDialog.setMessage(
                        "Uploaded "
                                + progress.toInt() + "%"
                    )
                }
        } else {
            Toast.makeText(this, "Please select Image !", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun getFileExtension(uri: Uri?): String? {
        val cR = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(cR.getType(uri!!))
    }

}