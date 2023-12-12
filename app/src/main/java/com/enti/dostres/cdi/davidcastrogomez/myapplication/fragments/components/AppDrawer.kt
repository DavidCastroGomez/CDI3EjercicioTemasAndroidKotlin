package com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.drawerlayout.widget.DrawerLayout.DrawerListener
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase.FB
import com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.screens.LoginScreen
import com.google.android.material.navigation.NavigationView

class AppDrawer: Fragment(), DrawerListener {

    companion object{
        private lateinit var Instance: AppDrawer
        fun get() = Instance
    }

    lateinit var fragmentView: View
    val drawer by lazy {fragmentView.findViewById<DrawerLayout>(R.id.AppDrawer)}
    val navigationDrawer by lazy {fragmentView.findViewById<NavigationView>(R.id.navigationDrawer)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Instance = this

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.component_navigationdrawer, container, false)

        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drawer.addDrawerListener(this)

        navigationDrawer.setNavigationItemSelectedListener { menuItem ->

            when(menuItem.itemId){
                R.id.login_drawer_button -> {

                    openLogin()

                    drawer.close()
                }

                R.id.profile_drawer_button -> {
                    //TODO ?
                }
            }

            true
        }
    }

    fun openLogin(){
        val loginScreen  = LoginScreen()
        val transaction = childFragmentManager.beginTransaction()
        transaction.setCustomAnimations(
            R.anim.enter_from_right, R.anim.exit_to_right,
            R.anim.enter_from_right, R.anim.exit_to_right)

        transaction.replace(R.id.reusableDialogsContainer, loginScreen)
            .addToBackStack(null)
            .commit()
    }

    fun checkLoginState(){
        val isLoginActive = FB.auth.isLoginActive()
        navigationDrawer.menu.findItem(R.id.login_drawer_button).isVisible = !isLoginActive
        navigationDrawer.menu.findItem(R.id.profile_drawer_button).isVisible = isLoginActive


    }

    fun openDrawer(){
        drawer.open()
    }

    override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
        checkLoginState()
    }

    override fun onDrawerOpened(drawerView: View) {
        checkLoginState()
    }

    override fun onDrawerClosed(drawerView: View) {
        //No
    }

    override fun onDrawerStateChanged(newState: Int) {
        //Aun menos
    }



}