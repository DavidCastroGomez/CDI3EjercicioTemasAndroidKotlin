package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase

import android.app.Application
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.models.DbUser
import com.google.firebase.auth.FirebaseAuth
import java.util.Date

class MyFirebaseAuth(val appContext: Application) {

    private val firebaseAuth = FirebaseAuth.getInstance()
    private var currentUser: DbUser? = null

    fun isLoginActive() = getUser() != null

    fun getUser() = currentUser

    fun setCurrentUser(newUser : DbUser){
        currentUser = newUser
    }

    fun getAuthDbUser() : DbUser?{

        firebaseAuth.currentUser?.let { user ->
            val dbUser = DbUser(
                id = user.uid,
                email = user.email,
                username = user.displayName,
                photoPath =  user.photoUrl.toString()
            )
            return dbUser
        }

        return null
    }

}