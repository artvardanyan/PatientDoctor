package com.insta.patientdoctor.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.insta.patientdoctor.R
import com.insta.patientdoctor.activities.PatientActivity
import com.insta.patientdoctor.modals.DoctorDetails
import com.squareup.picasso.Picasso
import java.io.File
import java.util.*

class MyAdapter(var context: Context, f: ArrayList<DoctorDetails>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    var doctordetails: ArrayList<DoctorDetails>
    var token = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.activity_select_doctor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        token++
        holder.nam.text = doctordetails[position].name
        holder.degre.text = doctordetails[position].qualification
        holder.phon.text = doctordetails[position].phone

        val url: String = doctordetails[position].url
        val nam: String = doctordetails[position].name
        val degree: String = doctordetails[position].qualification

        Picasso.get().load(url).into(holder.i)

        holder.sel.setOnClickListener {
            val i = Intent(context, PatientActivity::class.java)
            val b = Bundle()
            b.putString("Name", nam)
            b.putString("Qual", degree)
            b.putInt("Token", token)
            b.putString("Url", url)
            i.putExtras(b)
            context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return doctordetails.size
    }

    inner class MyViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var nam: TextView = itemView.findViewById<View>(R.id.docname) as TextView
        var degre: TextView = itemView.findViewById<View>(R.id.degree) as TextView
        var phon: TextView = itemView.findViewById<View>(R.id.phone) as TextView
        var i: ImageView = itemView.findViewById<View>(R.id.image) as ImageView
        var linearLayout: LinearLayout? = null
        var sel: Button = itemView.findViewById<View>(R.id.select) as Button

    }

    fun filterList(filteredList: ArrayList<DoctorDetails>) {
        doctordetails = filteredList
        notifyDataSetChanged()
    }

    init {
        doctordetails = f
    }
}