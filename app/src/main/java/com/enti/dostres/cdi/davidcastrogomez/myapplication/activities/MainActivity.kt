package com.enti.dostres.cdi.davidcastrogomez.myapplication.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase.FB

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity_screen)

        FB.crashlytics.logSimpleError("Main activity Error"){
            key("Error", "Kernel Panic")
            key("papopepo", true)
        }

    }

}