package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase

import android.app.Application

typealias FB = MyFirebase

class MyFirebase {

    companion object{

        lateinit var analytics: MyFirebaseAnalytics
        val crashlytics =  MyCrashlytics()

        fun init(appContext: Application){
            analytics = MyFirebaseAnalytics(appContext)
        }

    }

}