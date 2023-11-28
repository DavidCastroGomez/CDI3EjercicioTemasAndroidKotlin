package com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.google.android.material.navigation.NavigationView

class AppDrawer: Fragment() {

    companion object{
        private lateinit var Instance: AppDrawer
        fun get() = Instance
    }

    lateinit var fragmentView: View
    val drawer by lazy {fragmentView.findViewById<DrawerLayout>(R.id.AppDrawer)}
    val navigationDrawer by lazy {fragmentView.findViewById<NavigationView>(R.id.navigationDrawerContainer)}

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
    }

    fun openDrawer(){
        drawer.open()
    }

}