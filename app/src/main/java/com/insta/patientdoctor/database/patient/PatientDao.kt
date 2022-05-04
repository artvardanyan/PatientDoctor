package com.insta.patientdoctor.database.patient

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.insta.patientdoctor.modals.PatientDetails

@Dao
interface PatientDao {

    @Insert
    fun insertPatient(patientDetails: PatientDetails)

    @Delete
    fun deletePatient(patientDetails: PatientDetails)

    @Query("SELECT * FROM patientDetails")
    fun getAllPatients() : MutableList<PatientDetails>

}