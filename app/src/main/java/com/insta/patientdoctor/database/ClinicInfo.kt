package com.insta.patientdoctor.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.insta.patientdoctor.database.doctor.DoctorDao
import com.insta.patientdoctor.database.patient.PatientDao
import com.insta.patientdoctor.modals.DoctorDetails
import com.insta.patientdoctor.modals.PatientDetails

const val USER_DATABASE = "user_database"

@Database(entities = [PatientDetails::class, DoctorDetails::class], version = 3)
abstract class ClinicInfo : RoomDatabase() {
    abstract fun doctorDao(): DoctorDao
    abstract fun patientDao(): PatientDao

    companion object {
        @Volatile
        private var INSTANCE: ClinicInfo? = null

        fun getDatabase(context: Context): ClinicInfo {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    ClinicInfo::class.java,
                    USER_DATABASE
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

}