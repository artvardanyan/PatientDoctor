package com.insta.patientdoctor.utilities

import android.annotation.SuppressLint
import android.app.Activity
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.insta.patientdoctor.receiver.NetworkStateChangeReceiver
import com.insta.patientdoctor.receiver.NetworkStateChangeReceiver.Companion.IS_NETWORK_AVAILABLE
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

object ConstantValues {
    var IS_USER_LOGGED_IN = false
    const val TYPE_TEXT_PLAIN = "text/plain"
    const val SHARE = "Share"
    const val SINGLE_HYPHEN = "-"

    fun isValidEmail(email1: String?): Boolean {
        val EMAIL_PATTERN = ("^([_A-Za-z0-9-+].{2,})+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
        val pattern = Pattern.compile(EMAIL_PATTERN)
        val matcher = pattern.matcher(email1)
        return !matcher.matches()
    }

    fun shareDeepLink(activity: Activity?, str: String?) {
        if (activity != null) {
            val intent =
                ShareCompat.IntentBuilder.from(activity).setType(TYPE_TEXT_PLAIN).setText(str)
                    .setChooserTitle(
                        SHARE as CharSequence
                    ).intent
            if (intent.resolveActivity(activity.packageManager) != null) {
                activity.startActivity(intent)
            }
        }
    }

    // Validating pincode
    fun isValidPincode(pass1: String?): Boolean {
        return pass1 == null || pass1.length != 6
    }

    // Validating password
    fun isValidPassword1(pass1: String?): Boolean {
        return pass1 == null || pass1.length <= 5
    }

    // validating password with retype password
    fun isValidPassword(pass1: String?): Boolean {
        val PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})"
        val pattern = Pattern.compile(PASSWORD_PATTERN)
        val matcher = pattern.matcher(pass1)
        return !matcher.matches()
    }

    //Validating Address
    fun validAddress(pass1: String): Boolean {
        val pat = "^[a-zA-Z0-9]+([-/:\\s,][a-zA-Z0-9]+)*?$".toRegex()
        return !pass1.matches(pat) || pass1.length <= 2
    }

    fun validSchool(pass1: String): Boolean {
        return !pass1.matches("^[a-zA-Z0-9]+(\\s[a-zA-Z0-9]+)*?$".toRegex()) || pass1.length <= 2
    }

    //Validtaing Names
    fun validateFirstName(firstName: String): Boolean {
        return !firstName.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)*?$".toRegex())
    }

    //Validating Mobile
    fun isValidMoblie(pass1: String?): Boolean {
        return pass1 == null || pass1.length != 10
    }

    fun isValidOTP(pass1: String?): Boolean {
        return pass1 == null || pass1.length != 6
    }

    private fun isStringValid(str: String?): Boolean {
        return str != null && !str.isEmpty() && str.trim { it <= ' ' }.length > 0
    }

    fun getFormattedDate(str: String?, j: String?): String {
        @SuppressLint("SimpleDateFormat") var spf = SimpleDateFormat("yyyy-MM-dd hh:mm:sss")
        var newDate: Date? = null
        try {
            newDate = spf.parse(j)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        spf = if (isStringValid(str)) {
            SimpleDateFormat(str, Locale.ENGLISH)
        } else {
            SimpleDateFormat(MyAppPrefsManager.DD_MMM_YYYY_DATE_FORMAT, Locale.ENGLISH)
        }
        return spf.format(newDate)
    }

    fun getAppVersion(context: Context): String {
        var app_ver = ""
        try {
            app_ver = context.packageManager.getPackageInfo(
                context.packageName, 0
            ).versionName
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return app_ver
    }

    fun internetCheck(context: Activity?) {
        val intentFilter = IntentFilter(NetworkStateChangeReceiver.NETWORK_AVAILABLE_ACTION)
        LocalBroadcastManager.getInstance(context!!).registerReceiver(object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                val isNetworkAvailable = intent.getBooleanExtra(IS_NETWORK_AVAILABLE, false)
                val networkStatus = if (isNetworkAvailable) "Available" else "Not Available"
                if (networkStatus == "Not Available") {
                    Toast.makeText(context, "No Internet Found", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, " Internet Found", Toast.LENGTH_SHORT).show()
                }
            }
        }, intentFilter)
    }
}
