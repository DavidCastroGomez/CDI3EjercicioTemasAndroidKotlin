package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase

import android.app.Application

typealias FB = MyFirebase

class MyFirebase {

    companion object{

        lateinit var analytics: MyFirebaseAnalytics
        val crashlytics =  MyCrashlytics()
        lateinit var auth: MyFirebaseAuth
        val db = MyFirebaseDatabase()

        fun init(appContext: Application){
            auth = MyFirebaseAuth(appContext)
            analytics = MyFirebaseAnalytics(appContext)

        }

    }

}