package com.insta.patientdoctor.activities

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.insta.patientdoctor.constants.Constants.PICK_IMAGE_REQUEST
import com.insta.patientdoctor.databinding.ActivityDoctorImageSelectedBinding
import java.io.IOException
import java.util.*


class DoctorImageSelectedActivity : AppCompatActivity() {

    private var storage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null
    private var databaseReference: DatabaseReference? = null
    private var firebaseDatabase: FirebaseDatabase? = null
    private var filePath: Uri? = null
    private lateinit var binding: ActivityDoctorImageSelectedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoctorImageSelectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        this.title = "Doctor Profile"

        val bundle = intent.extras
        val nam = bundle!!.getString("Name")
        val degree = bundle.getString("Qualif")
        val phon = bundle.getString("phone")
        val dep = bundle.getString("dept")

        databaseReference = FirebaseDatabase.getInstance().reference.child("Doctor Details")
        storage = FirebaseStorage.getInstance()
        storageReference = storage!!.reference

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
        binding.doctorname.text = nam
        binding.doctordegree.text = degree
        binding.doctordept.text = dep
        binding.doctorphone.text = phon


        binding.upload.setOnClickListener {
            selectImage()
            uploadImage();
        }

        binding.submit.setOnClickListener { uploadImage() }

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
                binding.docimage.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    private fun uploadImage() {
        if (filePath != null) {
            val bundle = intent.extras
            val dep = bundle?.getString("dept")
            val no = bundle?.getString("id")
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