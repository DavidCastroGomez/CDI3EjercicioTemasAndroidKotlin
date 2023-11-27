package com.enti.dostres.cdi.davidcastrogomez.myapplication

import android.app.Application
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase.FB
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase.MyFirebase

class MyApp : Application() {

    companion object{
        private lateinit var instance : MyApp

        fun get() = instance

    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        FB.init(this)

        FB.analytics.logOpenApp()

    }
}