package com.insta.patientdoctor.modals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PatientDetails(
    var name: String,
    @PrimaryKey
    var pid: String,
    var gender: String?,
    var age: String?,
    var address: String,
    var phone: String,
    var prob: String
)
