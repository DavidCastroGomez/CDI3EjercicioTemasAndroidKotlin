package com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.google.android.gms.common.SignInButton
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textfield.TextInputLayout

class LogScreen : Fragment() {

    lateinit var fragmentView: View

    val usernameContainer by lazy { fragmentView.findViewById<MaterialCardView>(R.id.usernameInputContainer)}
    val usernameInput by lazy { fragmentView.findViewById<TextInputLayout>(R.id.usernameInput)}

    val passwordContainer by lazy { fragmentView.findViewById<MaterialCardView>(R.id.passwordInputContainer)}
    val passwordInput by lazy { fragmentView.findViewById<TextInputLayout>(R.id.passwordInput)}

    val verifyPasswordContainer by lazy { fragmentView.findViewById<MaterialCardView>(R.id.verifyPasswordInputContainer)}
    val verifyPasswordInput by lazy { fragmentView.findViewById<TextInputLayout>(R.id.verifyasswordInput)}

    val emailLoginButton by lazy { fragmentView.findViewById<MaterialButton>(R.id.loginButton) }
    val registerButton by lazy { fragmentView.findViewById<MaterialButton>(R.id.registerButton) }
    val googleAuthButton by lazy { fragmentView.findViewById<SignInButton>(R.id.login_google_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.login_screen, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        emailLoginButton.setOnClickListener{ emailLogin()}
        registerButton.setOnClickListener{ startRegister()}
        googleAuthButton.setOnClickListener{ googleAuth()}
    }

    private fun emailLogin(){

    }

    private fun startRegister(){
        verifyPasswordContainer.visibility = View.VISIBLE

        emailLoginButton.text = getString(R.string.back_to_login_button)
        registerButton.text = getString(R.string.end_register_button)

        emailLoginButton.setOnClickListener{
            verifyPasswordContainer.visibility = View.GONE

            emailLoginButton.text = getString(R.string.login_button)
            registerButton.text = getString(R.string.register_button)

            emailLoginButton.setOnClickListener{ emailLogin()}
            registerButton.setOnClickListener{ startRegister()}
        }

        registerButton.setOnClickListener{endRegister()}
    }

    private fun endRegister(){

    }

    private fun googleAuth(){

    }

}