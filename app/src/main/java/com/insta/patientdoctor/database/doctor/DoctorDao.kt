package com.insta.patientdoctor.database.doctor

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.insta.patientdoctor.modals.DoctorDetails
import com.insta.patientdoctor.modals.PatientDetails

@Dao
interface DoctorDao {

    @Insert
    fun insertDoctor(doctorDetails: DoctorDetails)

    @Delete
    fun deleteDoctor(doctorDetails: DoctorDetails)

    @Query("SELECT * FROM doctorDetails")
    fun getAllDoctors() : MutableList<DoctorDetails>
}