package com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class AppBottomBar : Fragment() {

    companion object{
        private lateinit var Instance : AppBottomBar
        fun get() = Instance
    }

    lateinit var bottomBar: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Instance = this
    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.component_bottombar, container, false)
        bottomBar = view.findViewById(R.id.AppNavigationBottomBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomBar.setOnItemSelectedListener { menuItem ->

            AppToolbar.get().toolbar.title = menuItem.title

            if(menuItem.itemId != bottomBar.selectedItemId){

                when(menuItem.itemId){
                    R.id.home_bottom_bar_button->{
                        AppNavHost.get().navHost.navigate(R.id.transition_chat_to_home)
                    }
                    R.id.chat_bottom_bar_button->{
                        AppNavHost.get().navHost.navigate(R.id.transition_home_to_chat)
                    }
                }
            }



            true
        }

        bottomBar.selectedItemId = bottomBar.menu.getItem(0).itemId
    }
}