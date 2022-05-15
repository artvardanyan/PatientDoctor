package com.insta.patientdoctor.modals

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable
