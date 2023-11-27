package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase

import com.google.firebase.Firebase
import com.google.firebase.crashlytics.KeyValueBuilder
import com.google.firebase.crashlytics.crashlytics
import com.google.firebase.crashlytics.setCustomKeys
import java.lang.Exception

class MyCrashlytics {

    private val crashlytics = Firebase.crashlytics

    fun logSimpleError(text: String, addExtraDataLambda: (KeyValueBuilder.() -> Unit)? = null){
        logError(Exception(text), addExtraDataLambda)
    }

    fun logError(exception: Exception, addExtraDataLambda: (KeyValueBuilder.() -> Unit)? = null)    {

        addExtraDataLambda?.let {lambda ->
            val builder = KeyValueBuilder(crashlytics)
            builder.lambda()
        }

        crashlytics.recordException(exception)

    }


    /*
        val exception = Exception("Test Error")
        Firebase.crashlytics.setCustomKeys {
            key("Param 1", "Hola")
            key("Param num", 1)
            key("Param bool", true)
            key("IsLogged", true)
            key("Username", "user")
        }
        Firebase.crashlytics.recordException(exception)
     */
}