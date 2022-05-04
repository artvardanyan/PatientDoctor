package com.insta.patientdoctor.utilities

import android.content.Context
import android.content.SharedPreferences

class MyAppPrefsManager(context: Context) {
    private val sharedPreferences: SharedPreferences
    private val prefsEditor: SharedPreferences.Editor
    var userId: String?
        get() = sharedPreferences.getString(USER_ID, null)
        set(userId) {
            prefsEditor.putString(USER_ID, userId)
            prefsEditor.commit()
        }
    var userName: String?
        get() = sharedPreferences.getString(USER_NAME, null)
        set(userName) {
            prefsEditor.putString(USER_NAME, userName)
            prefsEditor.commit()
        }
    var userMobile: String?
        get() = sharedPreferences.getString(USER_MOBILE, null)
        set(userMobile) {
            prefsEditor.putString(USER_MOBILE, userMobile)
            prefsEditor.commit()
        }
    var userPincode: String?
        get() = sharedPreferences.getString(USER_PINCODE, null)
        set(userPincode) {
            prefsEditor.putString(USER_PINCODE, userPincode)
            prefsEditor.commit()
        }
    var userLanguageCode: String?
        get() = sharedPreferences.getString(USER_LANGUAGE_CODE, "en")
        set(langCode) {
            prefsEditor.putString(USER_LANGUAGE_CODE, langCode)
            prefsEditor.commit()
        }
    var isUserLoggedIn: Boolean
        get() = sharedPreferences.getBoolean(IS_USER_LOGGED_IN, false)
        set(isUserLoggedIn) {
            prefsEditor.putBoolean(IS_USER_LOGGED_IN, isUserLoggedIn)
            prefsEditor.commit()
        }
    var isFirstTimeLaunch: Boolean
        get() = sharedPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTimeLaunch) {
            prefsEditor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTimeLaunch)
            prefsEditor.commit()
        }
    var isPushNotificationsEnabled: Boolean
        get() = sharedPreferences.getBoolean(IS_PUSH_NOTIFICATIONS_ENABLED, true)
        set(isPushNotificationsEnabled) {
            prefsEditor.putBoolean(IS_PUSH_NOTIFICATIONS_ENABLED, isPushNotificationsEnabled)
            prefsEditor.commit()
        }
    var isLocalNotificationsEnabled: Boolean
        get() = sharedPreferences.getBoolean(IS_LOCAL_NOTIFICATIONS_ENABLED, true)
        set(isLocalNotificationsEnabled) {
            prefsEditor.putBoolean(IS_LOCAL_NOTIFICATIONS_ENABLED, isLocalNotificationsEnabled)
            prefsEditor.commit()
        }
    var localNotificationsTitle: String?
        get() = sharedPreferences.getString(LOCAL_NOTIFICATIONS_TITLE, "Android Ecommerce")
        set(localNotificationsTitle) {
            prefsEditor.putString(LOCAL_NOTIFICATIONS_TITLE, localNotificationsTitle)
            prefsEditor.commit()
        }
    var localNotificationsDuration: String?
        get() = sharedPreferences.getString(LOCAL_NOTIFICATIONS_DURATION, "day")
        set(localNotificationsDuration) {
            prefsEditor.putString(LOCAL_NOTIFICATIONS_DURATION, localNotificationsDuration)
            prefsEditor.commit()
        }
    var localNotificationsDescription: String?
        get() = sharedPreferences.getString(
            LOCAL_NOTIFICATIONS_DESCRIPTION,
            "Check bundle of new Products"
        )
        set(localNotificationsDescription) {
            prefsEditor.putString(LOCAL_NOTIFICATIONS_DESCRIPTION, localNotificationsDescription)
            prefsEditor.commit()
        }

    companion object {
        private const val PREF_NAME = "PERSONALBANK"
        private const val USER_ID = "user_id"
        private const val USER_NAME = "user_name"
        private const val USER_MOBILE = "user_mobile"
        private const val USER_PINCODE = "user_pincode"
        private const val USER_LANGUAGE_CODE = "language_Code"
        private const val IS_USER_LOGGED_IN = "isLogged_in"
        private const val IS_FIRST_TIME_LAUNCH = "IsFirstTimeLaunch"
        private const val IS_PUSH_NOTIFICATIONS_ENABLED = "isPushNotificationsEnabled"
        private const val IS_LOCAL_NOTIFICATIONS_ENABLED = "isLocalNotificationsEnabled"
        private const val LOCAL_NOTIFICATIONS_TITLE = "localNotificationsTitle"
        private const val LOCAL_NOTIFICATIONS_DURATION = "localNotificationsDuration"
        private const val LOCAL_NOTIFICATIONS_DESCRIPTION = "localNotificationsDescription"
        const val DD_MMM_YYYY_DATE_FORMAT = "dd MMM yyyy"
    }

    init {
        val PRIVATE_MODE = 0
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        prefsEditor = sharedPreferences.edit()
        prefsEditor.apply()
    }
}
