package com.insta.patientdoctor.modals

import androidx.room.Entity
import androidx.room.PrimaryKey

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
)
