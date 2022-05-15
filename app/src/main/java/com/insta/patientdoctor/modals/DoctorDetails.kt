package com.insta.patientdoctor.modals

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class DoctorDetails(
    @PrimaryKey
    var name: String,
    var qualification: String,
    var gender: String,
    var age: String,
    var address: String,
    var phone: String?,
    var dept: String?,
    var url: String
) : Parcelable
