package com.insta.patientdoctor.utilities

import android.content.Context
import android.net.ConnectivityManager


object DetectConnection {
    fun checkInternetConnection(context: Context): Boolean {
        // detect internet connection
        val con_manager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return (con_manager.activeNetworkInfo != null && con_manager.activeNetworkInfo!!.isAvailable
                && con_manager.activeNetworkInfo!!.isConnected)
    }
}