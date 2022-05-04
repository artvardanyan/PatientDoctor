package com.insta.patientdoctor.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.insta.patientdoctor.database.ClinicInfo
import com.insta.patientdoctor.database.USER_DATABASE
import com.insta.patientdoctor.modals.DoctorDetails
import com.insta.patientdoctor.modals.PatientDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DoctorViewModel : ViewModel() {

    private lateinit var db: ClinicInfo
    val _listUsers = MutableLiveData<List<DoctorDetails>>()
    //val listUsers: LiveData<List<DoctorDetails>> = _listUsers

    fun createDataBase(context: Context) {
        db = Room.databaseBuilder(context, ClinicInfo::class.java, USER_DATABASE).fallbackToDestructiveMigration().build()
    }

    fun getDbUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            val doctor = db.doctorDao().getAllDoctors()
            _listUsers.postValue(doctor)
            Log.i("TAG", "OK")
        }
    }


}