package com.insta.patientdoctor.activities

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.insta.patientdoctor.R

class SplashScreenActivity : Activity() {

    var progressBar: ProgressBar? = null
    private val REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124

    //MyAppPrefsManager myAppPrefsManager;
    override fun onPause() {
        super.onPause()
    }

    var tryAgain: Button? = null

    // ImageView splashImage;
    var errorText: TextView? = null
    var internetNotAvailable: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)



    }
}